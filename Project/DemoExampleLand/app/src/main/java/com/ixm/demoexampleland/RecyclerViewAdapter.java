package com.ixm.demoexampleland;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<DataModel> mValues;
    Context mContext;
    protected ItemListener mListener;
    protected View.OnClickListener clickListener;

    public RecyclerViewAdapter(Context context, ArrayList<DataModel> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    /*  public RecyclerViewAdapter(Context context, ArrayList<DataModel> values, View.OnClickListener clickListener) {

          mValues = values;
          mContext = context;
          clickListener=clickListener;
      }*/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        DataModel item;

        public ViewHolder(View v) {

            super( v );

            v.setOnClickListener( this );
            textView = (TextView) v.findViewById( R.id.textView );
            imageView = (ImageView) v.findViewById( R.id.imageView );
            relativeLayout = (RelativeLayout) v.findViewById( R.id.relativeLayout );

        }

        public void setData(DataModel item) {
            this.item = item;

            textView.setText( item.text );
            imageView.setImageResource( item.drawable );
            relativeLayout.setBackgroundColor( Color.parseColor( item.color ) );

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                //  mListener.onItemClick(item);
                mListener.onItemClick( item, this.getPosition() );
             //   mListener.onItemClick( item, this.getPosition(), (int) getItemId() );
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( mContext ).inflate( R.layout.recycler_view_item, parent, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData( mValues.get( position ) );

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(DataModel item, int position);

     //   void onItemClick(DataModel item, int position, int ItemId);
        //    void onItemClick(DataModel item, int position,int count);


    }
}