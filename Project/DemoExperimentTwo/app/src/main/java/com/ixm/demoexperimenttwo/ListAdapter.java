package com.ixm.demoexperimenttwo;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ArrayList<DataModel> mValues;
    Context mContext;
    protected ItemListener mListener;

    public ListAdapter(Context context, ArrayList<DataModel> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        Context context;
        Button btn;
        public CardView linearLayout;
        DataModel item;
        MediaPlayer mp;

        public ViewHolder(View v) {

            super( v );

            v.setOnClickListener( this );
            textView = (TextView) v.findViewById( R.id.textView );
            btn = v.findViewById( R.id.btn );
      linearLayout = v.findViewById( R.id.cardView );
            mp = MediaPlayer.create(mContext, R.raw.sound);

            btn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Toast.makeText(mContext, "Button Clicked!!!", Toast.LENGTH_SHORT ).show();
                        if (mp.isPlaying()) {
                            mp.stop();
                            mp.release();
                            mp = MediaPlayer.create(context, R.raw.sound);
                        } mp.start();
                    } catch(Exception e) { e.printStackTrace(); }

                }
            } );

        }

        public void setData(DataModel item) {
            this.item = item;
            textView.setText( item.text );
          linearLayout.setBackgroundColor( Color.parseColor( item.color ) );

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick( item );

            }
        }


       /* @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick( item );
            }
        }*/
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( mContext ).inflate( R.layout.row_detail, parent, false );

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
        void onItemClick(DataModel item);
    }
}