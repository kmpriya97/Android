package producttracking.iexemplar.com.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.app.ApplicationContext;
import producttracking.iexemplar.com.mqtt.MQTTServerClient;
import producttracking.iexemplar.com.mqtt.ServerCallBack;
import producttracking.iexemplar.com.service.APIInterface;
import producttracking.iexemplar.com.service.RetrofitClient;
import producttracking.iexemplar.com.service.model.Indent;
import producttracking.iexemplar.com.service.model.ProcessStatus;
import producttracking.iexemplar.com.service.model.StatusDatum;
import producttracking.iexemplar.com.service.model.TowelStatus;
import producttracking.iexemplar.com.service.model.TowelStatusDatum;
import producttracking.iexemplar.com.utils.ConnectionDetector;
import producttracking.iexemplar.com.utils.DialogUtil;
import producttracking.iexemplar.com.utils.ProgressUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*******************************************************************************
 * Created by iExemplar on ${Date}.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public class Dashboard extends Fragment implements ApplicationContext, ServerCallBack {

    @BindView(R.id.spinnerOrder)
    Spinner spinnerIndent;

    @BindView(R.id.spinnerFabric)
    Spinner spinnerFabric;

    @BindView(R.id.pbCircle)
    ProgressBar progressBarCircle;

    @BindView(R.id.tvProgress)
    TextView tvProgress;

    @BindView(R.id.progressDept)
    ProgressBar progressBarDept;

    @BindView(R.id.tvd1)
    TextView tvd1;

    @BindView(R.id.tvd2)
    TextView tvd2;

    @BindView(R.id.tvd3)
    TextView tvd3;

    @BindView(R.id.tvd4)
    TextView tvd4;

    @BindView(R.id.tvd5)
    TextView tvd5;

    @BindView(R.id.tvd6)
    TextView tvd6;


    @BindView(R.id.tvTime1)
    TextView tvTime1;

    @BindView(R.id.tvTime2)
    TextView tvTime2;

    @BindView(R.id.tvTime3)
    TextView tvTime3;

    @BindView(R.id.tvTime4)
    TextView tvTime4;

    @BindView(R.id.tvTime5)
    TextView tvTime5;

    @BindView(R.id.tvTime6)
    TextView tvTime6;

    @BindView(R.id.tvlcv1)
    TextView tvlcv1;

    @BindView(R.id.tvlcv2)
    TextView tvlcv2;

    @BindView(R.id.tvlcv3)
    TextView tvlcv3;

    @BindView(R.id.tvlctotal)
    TextView tvlctotal;

    @BindView(R.id.tvsv1)
    TextView tvsv1;

    @BindView(R.id.tvsv2)
    TextView tvsv2;

    @BindView(R.id.tvstotal)
    TextView tvstotal;


    private int progressStatus = 0;
    private int progressStatusFab = 0;

    private int spinCnt = -1;
    private String strCurrentIndent = "", strCurrentFabric = "";
    private String strProcessFabricName = "", strProcessFabricTime = "";
    private String strProcessName = "", strProcessType = "";

    ConnectionDetector connectionDetector;
    ProgressDialog mProgressDialog;
    APIInterface apiInterface;
    Activity mContext;

   // List<Indent> responseIndent = new ArrayList<>();
     List<Indent> responseIndent = new ArrayList<>();
   //  List<Indent> responseFabric = new ArrayList<>();
     List<String> responseFabric = new ArrayList<>();

     List<String> responseItems = new ArrayList<>();


    private static final String LIVE_CROSS_TOPIC = "SST/desktopOne/id";
    private static final String LIVE_STICHING_TOPIC = "SST/desktopTwo/id";

    // MQTT objects
    MqttAndroidClient mqttAndroidClient = null;
    MQTTServerClient mqttServerClient = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        setWidgest();
        getIndentOrdersList();
        getInitialConfig();
        return rootView;
    }
    private void setWidgest() {
        responseItems.add("E20096D117AEDC48E7E8140C"); //Fab 1
        responseItems.add("E20096D1164B1E88E7E45FF0"); //Fab 2
        responseItems.add("E20096D1154C9948E7E1A26D"); //Fab 3

        responseItems.add("E2004080770A0230128095E0"); //OP 1
        responseItems.add("E2004080770A0235130095F2"); //OP 2
        responseItems.add("E2004080770A023622502C55"); //OP 3
        responseItems.add("E73B7FFF44BDBF59F7D5"); //OP 4
        responseItems.add("E73B7FFF44BDBFB2B775"); //OP 5
        responseItems.add("E73B7FFF44BDBFB6F7AA"); //OP 6

        tvlcv1.setText("0");
        tvlcv2.setText("0");
        tvlcv3.setText("0");
        tvlctotal.setText("0");

        tvsv1.setText("0");
        tvsv2.setText("0");
        tvstotal.setText("0");


    }
    @OnItemSelected(R.id.spinnerOrder)
    public void spinnerItemSelected(Spinner spinner, int position) {
        spinCnt = spinCnt + 1;
        if (spinCnt > 0) {
            boolean isConnected = connectionDetector.isConnectingToInternet();
            if (isConnected) {
                mProgressDialog = ProgressUtil.showDialog(mContext);
                strCurrentIndent = "";
                strProcessName = "";
                strProcessType = "";
                strCurrentFabric = "";
                strProcessFabricName = "";
                strProcessFabricTime = "";
                progressStatus = 0;
                progressStatusFab = 0;
                progressBarCircle.setProgress( progressStatus );
                progressBarDept.setProgress(progressStatusFab);
                tvProgress.setText( progressStatus + "%" );
                tvd1.setTextColor(getResources().getColor(R.color.colorout));
                tvd2.setTextColor(getResources().getColor(R.color.colorout));
                tvd3.setTextColor(getResources().getColor(R.color.colorout));
                tvd4.setTextColor(getResources().getColor(R.color.colorout));
                tvd5.setTextColor(getResources().getColor(R.color.colorout));
                tvd6.setTextColor(getResources().getColor(R.color.colorout));
                tvlcv1.setText("0");
                tvlcv2.setText("0");
                tvlcv3.setText("0");
                tvlctotal.setText("0");
                tvsv1.setText("0");
                tvsv2.setText("0");
                tvstotal.setText("0");
                spinnerFabric.setAdapter(null);

                getIndentStatus(spinnerIndent.getSelectedItem().toString());
            }

        }
    }

    public void getInitialConfig(){
        //Toast.makeText(getActivity(),"Hi",Toast.LENGTH_LONG).show();
        Log.e("", "getInitialConfig: " );
        mqttServerClient = new MQTTServerClient();
        mqttServerClient.mqttCallBack(this);
    }

    @Override
    public void onResume() {
        //Toast.makeText(getActivity(),"onresume",Toast.LENGTH_LONG).show();
        super.onResume();
        mqttServerClient.clientConnect(mContext);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Toast.makeText(getActivity(),"pause",Toast.LENGTH_LONG).show();
        try {
            if (mqttAndroidClient != null) {
                mqttAndroidClient.unregisterResources();
                mqttAndroidClient.close();
            }
        } catch (Exception e) {
            Log.e(EXCEPTION, e.toString());

        }
    }

    private void getIndentOrdersList() {
        connectionDetector = new ConnectionDetector(mContext);
        apiInterface = RetrofitClient.getClientJson();
        boolean isConnected = connectionDetector.isConnectingToInternet();
        if (isConnected) {
            mProgressDialog = ProgressUtil.showDialog(mContext);
            apiInterface.getIndentList().enqueue(new Callback<List<Indent>>() {
                @Override
                public void onResponse(Call<List<Indent>> list, Response<List<Indent>> response) {
                    Log.e("succ", "successParsing: " + response.code());

                    responseIndent.addAll(response.body());
                    Log.e("resp", "onResponse: " + responseIndent);
                    Log.e("count", "onResponse: " + responseIndent.size());
                    Log.d("JSON  LIST", list.toString());
                    strCurrentIndent = showListinSpinner();
                    //salesOrderAdapter.notifyDataSetChanged();
                    if (!strCurrentIndent.equals("")) {
                        getIndentStatus(strCurrentIndent);
                    } else {
                        mProgressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<List<Indent>> call, Throwable t) {
                    // Log.i("onFailure: ", t.getMessage());
                    mProgressDialog.dismiss();
                    new DialogUtil(mContext, "Internal Server Error").show();
                }
            });

        } else {
            new DialogUtil(mContext, getResources().getString(R.string.no_internet_connection)).show();
        }
    }


    private void drawProcessCircle(String procssName, String procType){
        if (procssName.equals("Picking") || procssName.equals("Warping")) {
            progressStatus = 10;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        } else if (procssName.equals("PileWarping")) {
            progressStatus = 20;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        } else if (procssName.equals("GroundWarping")) {
            progressStatus = 30;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        } else if (procssName.equals("Sizing")) {
            progressStatus = 40;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        } else if (procssName.equals("Weaving")) {
            progressStatus = 50;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        } else if (procssName.equals("Inspection") || procssName.equals("Confection")) {
            progressStatus = 60;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        } else if (procssName.equals("LengthCutting")) {
            progressStatus = 70;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        }  else if (procssName.equals("Hemming")) {
            progressStatus = 80;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        }  else if (procssName.equals("CrossCutting")) {
            progressStatus = 90;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        }  else if (procssName.equals("Stitching")) {
            progressStatus = 100;
            progressBarCircle.setProgress( progressStatus );
            tvProgress.setText( progressStatus + "%" );

        }
    }

    private void drawProcessDept(String procssName, String time){
         if (procssName.equals("Weaving")) {
            progressStatusFab = 16;
            progressBarDept.setProgress(progressStatusFab);
            tvd1.setTextColor(getResources().getColor(R.color.colorFilled));
            tvd2.setTextColor(getResources().getColor(R.color.colorout));
            tvd3.setTextColor(getResources().getColor(R.color.colorout));
            tvd4.setTextColor(getResources().getColor(R.color.colorout));
            tvd5.setTextColor(getResources().getColor(R.color.colorout));
            tvd6.setTextColor(getResources().getColor(R.color.colorout));


        } else if (procssName.equals("Inspection")) {
             progressStatusFab = 32;
             progressBarDept.setProgress( progressStatusFab);
             tvd1.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd2.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd3.setTextColor(getResources().getColor(R.color.colorout));
             tvd4.setTextColor(getResources().getColor(R.color.colorout));
             tvd5.setTextColor(getResources().getColor(R.color.colorout));
             tvd6.setTextColor(getResources().getColor(R.color.colorout));

        } else if (procssName.equals("LengthCutting")) {
             progressStatusFab = 48;
             progressBarDept.setProgress( progressStatusFab );
             tvd1.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd2.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd3.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd4.setTextColor(getResources().getColor(R.color.colorout));
             tvd5.setTextColor(getResources().getColor(R.color.colorout));
             tvd6.setTextColor(getResources().getColor(R.color.colorout));

        }  else if (procssName.equals("Hemming")) {
             progressStatusFab = 64;
             progressBarDept.setProgress( progressStatusFab );
             tvd1.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd2.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd3.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd4.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd5.setTextColor(getResources().getColor(R.color.colorout));
             tvd6.setTextColor(getResources().getColor(R.color.colorout));

        }  else if (procssName.equals("CrossCutting")) {
             progressStatusFab = 80;
             progressBarDept.setProgress( progressStatusFab );
             tvd1.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd2.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd3.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd4.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd5.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd6.setTextColor(getResources().getColor(R.color.colorout));

        }  else if (procssName.equals("Stitching")) {
             progressStatusFab = 100;
             progressBarDept.setProgress( progressStatusFab );
             tvd1.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd2.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd3.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd4.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd5.setTextColor(getResources().getColor(R.color.colorFilled));
             tvd6.setTextColor(getResources().getColor(R.color.colorFilled));

        }
    }

    private void getIndentStatus(final String indentNum) {
        connectionDetector = new ConnectionDetector(mContext);
        apiInterface = RetrofitClient.getClient();
        boolean isConnected = connectionDetector.isConnectingToInternet();
        if (isConnected) {
            apiInterface.checkStatus(indentNum, "dashboard").enqueue(new Callback<ProcessStatus>() {
                @Override
                public void onResponse(Call<ProcessStatus> list, Response<ProcessStatus> response) {
                    //Log.e("succ", "successParsing: " + response.code());
                    Log.e("dashboard Status", "dashboard Status: " + response.code());
                    if (response.code() == 200) {
                        if (response.body().getCode() == 200) {
                            StatusDatum statusDatum = response.body().getData().get(0);
                            Log.e("test", "onResponse: process " + statusDatum.getProcess());
                            Log.e("test", "onResponse: processType " + statusDatum.getProcessType());
                            Log.e("test", "onResponse: fabricId " + statusDatum.getFabric());
                            strProcessType = statusDatum.getProcessType();
                            strProcessName = statusDatum.getProcess();
                            if(statusDatum.getFabric() != null) {
                                strCurrentFabric = statusDatum.getFabric();
                                responseFabric.add(strCurrentFabric);
                                showListinSpinnerFab();
                            }
                            drawProcessCircle(strProcessName, strProcessType);
                            if (!strCurrentFabric.equals("")) {
                                getDeptStatus(indentNum);
                                return;
                            }
                            mProgressDialog.dismiss();
                        } else {
                            mProgressDialog.dismiss();
                            new DialogUtil(mContext,response.body().getMessage()).show();
                        }

                    } else {
                        mProgressDialog.dismiss();
                        new DialogUtil(mContext, response.message()).show();
                    }

                }

                @Override
                public void onFailure(Call<ProcessStatus> call, Throwable t) {
                    //   Log.i("onFailure: ", t.getMessage());

                    mProgressDialog.dismiss();
                    new DialogUtil(mContext, "Internal Server Error").show();
                }
            });

        } else {
            new DialogUtil(mContext, getResources().getString(R.string.no_internet_connection)).show();
        }
    }

    private void getDeptStatus(final String indentNum) {
        connectionDetector = new ConnectionDetector(mContext);
        apiInterface = RetrofitClient.getClient();
        boolean isConnected = connectionDetector.isConnectingToInternet();
        if (isConnected) {
            apiInterface.checkStatus(indentNum, "deptProgress").enqueue(new Callback<ProcessStatus>() {
                @Override
                public void onResponse(Call<ProcessStatus> list, Response<ProcessStatus> response) {
                    //Log.e("succ", "successParsing: " + response.code());
                    Log.e("deptProgress Status", "deptProgress Status: " + response.code());
                    if (response.code() == 200) {
                        if (response.body().getCode() == 200) {
                            StatusDatum statusDatum = response.body().getData().get(0);
                            Log.e("test", "onResponse: process " + statusDatum.getProcess());
                            Log.e("test", "onResponse: processType " + statusDatum.getProcessType());
                            strProcessFabricName = statusDatum.getProcess();
                            drawProcessDept(strProcessFabricName, strProcessFabricTime);
                            //strProcessName = statusDatum.getProcess();
                           // mProgressDialog.dismiss();
                            getCrossCutting(indentNum);
                        } else {
                            mProgressDialog.dismiss();
                            new DialogUtil(mContext,response.body().getMessage()).show();
                        }

                    } else {
                        mProgressDialog.dismiss();
                        new DialogUtil(mContext, response.message()).show();
                    }

                }

                @Override
                public void onFailure(Call<ProcessStatus> call, Throwable t) {
                    //   Log.i("onFailure: ", t.getMessage());

                    mProgressDialog.dismiss();
                    new DialogUtil(mContext, "Internal Server Error").show();
                }
            });

        } else {
            new DialogUtil(mContext, getResources().getString(R.string.no_internet_connection)).show();
        }
    }

    private void getCrossCutting(final String indentNum) {
        connectionDetector = new ConnectionDetector(mContext);
        apiInterface = RetrofitClient.getClient();
        boolean isConnected = connectionDetector.isConnectingToInternet();
        if (isConnected) {
            apiInterface.getCrossCut(indentNum).enqueue(new Callback<TowelStatus>() {
                @Override
                public void onResponse(Call<TowelStatus> list, Response<TowelStatus> response) {
                    //Log.e("succ", "successParsing: " + response.code());
                    Log.e("dashboard Status", "dashboard Status: " + response.code());
                    if (response.code() == 200) {
                        if (response.body().getCode() == 200) {
                            TowelStatusDatum towelStatusDatum = response.body().getData().get(0);
                            Log.e("test", "onResponse: process " + towelStatusDatum.getGradeA());
                            Log.e("test", "onResponse: processType " + towelStatusDatum.getGradeB());
                            Log.e("test", "onResponse: fabricId " + towelStatusDatum.getGradeC());
                            tvlcv1.setText(String.valueOf(towelStatusDatum.getGradeA()));
                            tvlcv2.setText(String.valueOf(towelStatusDatum.getGradeB()));
                            tvlcv3.setText(String.valueOf(towelStatusDatum.getGradeC()));
                            tvlctotal.setText(String.valueOf(towelStatusDatum.getTotal()));
                            getStichCutting(indentNum);

                           // mProgressDialog.dismiss();
                        } else {
                            mProgressDialog.dismiss();
                            new DialogUtil(mContext,response.body().getMessage()).show();
                        }

                    } else {
                        mProgressDialog.dismiss();
                        new DialogUtil(mContext, response.message()).show();
                    }

                }

                @Override
                public void onFailure(Call<TowelStatus> call, Throwable t) {
                    //   Log.i("onFailure: ", t.getMessage());

                    mProgressDialog.dismiss();
                    new DialogUtil(mContext, "Internal Server Error").show();
                }
            });

        } else {
            new DialogUtil(mContext, getResources().getString(R.string.no_internet_connection)).show();
        }
    }

    private void getStichCutting(final String indentNum) {
        connectionDetector = new ConnectionDetector(mContext);
        apiInterface = RetrofitClient.getClient();
        boolean isConnected = connectionDetector.isConnectingToInternet();
        if (isConnected) {
            apiInterface.getStichCut(indentNum).enqueue(new Callback<TowelStatus>() {
                @Override
                public void onResponse(Call<TowelStatus> list, Response<TowelStatus> response) {
                    //Log.e("succ", "successParsing: " + response.code());
                    Log.e("dashboard Status", "dashboard Status: " + response.code());
                    if (response.code() == 200) {
                        if (response.body().getCode() == 200) {
                            TowelStatusDatum towelStatusDatum = response.body().getData().get(0);
                            Log.e("test", "onResponse: process " + towelStatusDatum.getGradeA());
                            Log.e("test", "onResponse: processType " + towelStatusDatum.getGradeB());
                            Log.e("test", "onResponse: fabricId " + towelStatusDatum.getGradeC());
                            tvsv1.setText(String.valueOf(towelStatusDatum.getGradeA()));
                            tvsv2.setText(String.valueOf(towelStatusDatum.getGradeB()));
                            tvstotal.setText(String.valueOf(towelStatusDatum.getTotal()));

                            mProgressDialog.dismiss();
                        } else {
                            mProgressDialog.dismiss();
                            new DialogUtil(mContext,response.body().getMessage()).show();
                        }

                    } else {
                        mProgressDialog.dismiss();
                        new DialogUtil(mContext, response.message()).show();
                    }

                }

                @Override
                public void onFailure(Call<TowelStatus> call, Throwable t) {
                    //   Log.i("onFailure: ", t.getMessage());

                    mProgressDialog.dismiss();
                    new DialogUtil(mContext, "Internal Server Error").show();
                }
            });

        } else {
            new DialogUtil(mContext, getResources().getString(R.string.no_internet_connection)).show();
        }
    }



    private String showListinSpinner() {
        //String array to store all the book names
        String iName = "";
        String[] items = new String[responseIndent.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < responseIndent.size(); i++) {
            //Storing names to string array
            items[i] = responseIndent.get(i).getNumber();
            if (i == 0) {
                iName = items[i];
            }
        }

        //Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, items);
        //setting adapter to spinner
        spinnerIndent.setAdapter(adapter);
        //Creating an array adapter for list view
        return  iName;

    }

    private void showListinSpinnerFab() {

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, responseFabric);
        //setting adapter to spinner
        spinnerFabric.setAdapter(adapter);
        //Creating an array adapter for list view

    }


    @Override
    public void onSuccess(MqttAndroidClient _mqttAndroidClient) {
        try {
            mqttAndroidClient = _mqttAndroidClient;
            Log.e(MQTT_CONNECTION, "Connected");
            if (mqttAndroidClient != null) {
                mqttServerClient.subscribe(mqttAndroidClient, LIVE_CROSS_TOPIC);
                mqttServerClient.subscribe(mqttAndroidClient, LIVE_STICHING_TOPIC);
            }
        } catch (NullPointerException e) {
            Log.e(MQTT_CONNECTION_FAILED, e.toString());
        } catch (Exception e) {
            Log.e(MQTT_CONNECTION_FAILED, e.toString());
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onMessageReceived(String topic, MqttMessage mqttMessage) {
        try {
            byte[] payload = mqttMessage.getPayload();
            String response = new String(payload);
            Log.i("MQTT Response", response);

            try {

                JSONObject obj = new JSONObject(response);

                Log.d("My App", obj.toString());
                Log.d("", "onMessageReceived: "+ obj.get("tagid"));
                Log.d("", "onMessageReceived: "+ obj.get("grade"));

                if (responseItems.contains(obj.get("tagid"))) {
                    System.out.println("Account found");
                } else {
                    System.out.println("Account not found");
                    if (topic.equals(LIVE_CROSS_TOPIC)) {
                        int gradeA = Integer.parseInt(tvlcv1.getText().toString());
                        int gradeB = Integer.parseInt(tvlcv2.getText().toString());
                        int gradeC = Integer.parseInt(tvlcv3.getText().toString());
                        if (obj.get("grade").equals("Grade A")) {
                            gradeA = gradeA + 1;
                            tvlcv1.setText(String.valueOf(gradeA));
                        } else  if (obj.get("grade").equals("Grade B")) {
                            gradeB = gradeB + 1;
                            tvlcv2.setText(String.valueOf(gradeB));
                        } else  if (obj.get("grade").equals("Grade C")) {
                            gradeC = gradeC + 1;
                            tvlcv3.setText(String.valueOf(gradeC));
                        }
                        int totalGrd = gradeA + gradeB + gradeC;
                        tvlctotal.setText(String.valueOf(totalGrd));
                    } else if (topic.equals(LIVE_STICHING_TOPIC)) {
                        int gradeA = Integer.parseInt(tvsv1.getText().toString());
                        int gradeB = Integer.parseInt(tvsv2.getText().toString());
                        if (obj.get("grade").equals("Grade A")) {
                            gradeA = gradeA + 1;
                            tvsv1.setText(String.valueOf(gradeA));
                        } else  if (obj.get("grade").equals("Grade B")) {
                            gradeB = gradeB + 1;
                            tvsv2.setText(String.valueOf(gradeB));
                        }
                        int totalGrd = gradeA + gradeB;
                        tvstotal.setText(String.valueOf(totalGrd));
                    }
                }
                //  Log.d("My App"+ );

            } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
            }

        }  catch (Exception e) {
            Log.e(MQTT_EXCEPTION, e.toString());
        }
    }

    @Override
    public void connectionLost(String error) {

    }

    @Override
    public void reconneted(MqttAndroidClient _mqttAndroidClient) {
        if (_mqttAndroidClient != null) {
            mqttAndroidClient = _mqttAndroidClient;
            mqttServerClient.subscribe(mqttAndroidClient, LIVE_CROSS_TOPIC);
            mqttServerClient.subscribe(mqttAndroidClient, LIVE_STICHING_TOPIC);
        }
    }
}
