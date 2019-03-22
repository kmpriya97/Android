package producttracking.iexemplar.com.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.adapter.SalesOrderAdapter;
import producttracking.iexemplar.com.app.ApplicationContext;
import producttracking.iexemplar.com.service.RetrofitClient;
import producttracking.iexemplar.com.ui.SalesOrderActivity;
import producttracking.iexemplar.com.utils.ConnectionDetector;
import producttracking.iexemplar.com.service.APIInterface;
import producttracking.iexemplar.com.utils.DialogUtil;
import producttracking.iexemplar.com.utils.ProgressUtil;
import producttracking.iexemplar.com.utils.Conversion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import producttracking.iexemplar.com.service.model.TowelDetail;
import producttracking.iexemplar.com.service.model.SalesOrder;

/*******************************************************************************
 * Created by iExemplar on ${Date}.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public class SalesOrderFragment extends Fragment implements ApplicationContext, SalesOrderAdapter.SalesOrderCallBack {

    ConnectionDetector connectionDetector;
    ProgressDialog mProgressDialog;
    APIInterface apiInterface;
    Activity mContext;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.rv_salesorder)
    RecyclerView recyclerView;

    List<SalesOrder> responseOrder = new ArrayList<>();
    SalesOrderAdapter salesOrderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sales_order_list, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        setListView();
        getInitialSetupSalesOrderList();
        return rootView;
    }

    private void setListView() {
        salesOrderAdapter = new SalesOrderAdapter(getActivity(), responseOrder);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        salesOrderAdapter.registerCallBack(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(salesOrderAdapter);
    }

    @OnClick(R.id.fab)
    public void setNewSalesOrder(View view) {
        Intent intent = new Intent(mContext, SalesOrderActivity.class);
        startActivity(intent);
        //startActivityForResult(intent, REQUEST_CODE_POST);
    }
//
//    private void getInitialSetupSalesOrderList() {
//        connectionDetector = new ConnectionDetector(mContext);
//        apiInterface = RetrofitClient.getClientJson();
//        boolean isConnected = connectionDetector.isConnectingToInternet();
//        if (isConnected) {
//            mProgressDialog = ProgressUtil.showDialog(mContext);
//            apiInterface.getSalesOrderList().enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    Log.e("results", "onResponseLogin: "+response.code());
//                    if (Conversion.successParsing(response.code())) {
//                        Log.e("succ", "successParsing: "+response.code());
//                        try {
//                            Log.e("body content", "successParsing: "+response.body().string());
//                           // Log.e("ARRAY", "onResponse: array ", array.contentLength();
//                             // JSONArray jsonArray = null;
//                            try {
//                               // jsonArray = new JSONArray(response.body().string());
//                                JSONArray jsonArray = new JSONArray(response.body().string());
//                                System.out.println("JSON ARRAY "+jsonArray);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                System.out.print(e);
//                            }
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            mProgressDialog.dismiss();
//                            new DialogUtil(mContext,"Error").show();
//                        }
//
//                    } else {
//                        mProgressDialog.dismiss();
//                        new DialogUtil(mContext,"Error").show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    mProgressDialog.dismiss();
//                }
//            });
//        } else {
//
//        }
//    }

    /*

    apiInterface.login(body).enqueue(new Callback<User>() {
     */

    private void getInitialSetupSalesOrderList() {
        connectionDetector = new ConnectionDetector(mContext);
        apiInterface = RetrofitClient.getClientJson();
        boolean isConnected = connectionDetector.isConnectingToInternet();
        if (isConnected) {
            mProgressDialog = ProgressUtil.showDialog(mContext);
            apiInterface.getSalesOrderList().enqueue(new Callback<List<SalesOrder>>() {
                @Override
                public void onResponse(Call<List<SalesOrder>> call, Response<List<SalesOrder>> response) {
                    Log.e("succ", "successParsing: " + response.code());
                    mProgressDialog.dismiss();
                    responseOrder.addAll(response.body());
                    salesOrderAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<SalesOrder>> call, Throwable t) {
                   // Log.i("onFailure: ", t.getMessage());

                    mProgressDialog.dismiss();
                }
            });

        } else {

        }
    }


    @Override
    public void clickRow(int position) {

    }
}
