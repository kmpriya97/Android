package com.ixm.demoexampleland;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class AdapterNew extends RecyclerView.Adapter<AdapterNew.MyViewHolder> {

    private List<ModelNew> configModelList;
    public AdapterNew(List<ModelNew> configModels) {
        Log.i( "@@@", "onCreateViewHolder: " );
        this.configModelList = configModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.new_list_item, parent, false );
        Log.i( "%%%", "onCreateViewHolder: " );
        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelNew configModel = configModelList.get( position );
        holder.name.setText( configModel.getItemName() );
        holder.count.setText( configModel.getItemCount() );
        /*configModelList.get(position).setItemName( holder.name.getText().toString());
        configModelList.get(position).setItemCount( holder.count.getText().toString() );*/
     //
       // holder.count.setText( configModel.getItemCount() );
        String Name = configModel.getItemName();
        String Count = configModel.getItemCount();
        Log.i( "Name", "onBindViewHolder: " + Name );
        Log.i( "Count", "onBindViewHolder: " + Count );
    }

    @Override
    public int getItemCount() {
        Log.i( "", "getItemCount:" + configModelList.size() );
        return configModelList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvList)
        TextView name;

        @BindView(R.id.tv_count)
        TextView count;

        public MyViewHolder(View view) {
            super( view );
            ButterKnife.bind( this, view );

        }
    }

}