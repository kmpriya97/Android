package producttracking.iexemplar.com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.service.model.SalesOrder;

/*******************************************************************************
 * Created by iExemplar on ${Date}.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public class SalesOrderAdapter extends RecyclerView.Adapter<SalesOrderAdapter.ViewHolder> {

    SalesOrderCallBack salesOrderCallBack;
    private List<SalesOrder> salesOrders;
    private Context context;

    public SalesOrderAdapter(Context context, List<SalesOrder> salesOrders) {
        this.salesOrders = salesOrders;
        this.context = context;
    }

    public void registerCallBack(SalesOrderCallBack salesOrderCallBack) {
        this.salesOrderCallBack = salesOrderCallBack;
    }


    @NonNull
    @Override
    public SalesOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_sales_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesOrderAdapter.ViewHolder viewHolder, final int i) {
        SalesOrder salesOrder = salesOrders.get(i);
        viewHolder.ausValue.setText(salesOrder.getNumber() + "," + salesOrder.getDate());
        viewHolder.countryValue.setText(salesOrder.getCountry());
        if (salesOrder.getTowelDetails().size() > 0) {
            viewHolder.yarnValue.setText(salesOrder.getTowelDetails().get(0).getTowelName());
            viewHolder.qtyValue.setText(salesOrder.getTowelDetails().get(0).getQuantity());
        } else {
            viewHolder.yarnValue.setText("N/A");
            viewHolder.qtyValue.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        return salesOrders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.aus_value)
        TextView ausValue;

        @BindView(R.id.yarn_value)
        TextView yarnValue;

        @BindView(R.id.country_value)
        TextView countryValue;

        @BindView(R.id.qty_value)
        TextView qtyValue;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public void updateListValues(int position) {
        notifyItemChanged(position);
    }


    public interface SalesOrderCallBack {

        void clickRow(int position);

    }


}
