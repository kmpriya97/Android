package producttracking.iexemplar.com.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.app.ApplicationContext;
import producttracking.iexemplar.com.mqtt.MQTTServerClient;
import producttracking.iexemplar.com.mqtt.ServerCallBack;

/*******************************************************************************
 * Created by iExemplar on ${Date}.
 * <p>
 * Copyright (c) 2016 iExemplar. All rights reserved.
 *******************************************************************************/

public class Configuration extends Fragment implements ApplicationContext, ServerCallBack {

    private static final String LIVE_STATUS_TOPIC = "terry/wallmount/id";
    private static final String LIVE_CROSS_TOPIC = "SST/desktopOne/id";
    private static final String LIVE_STICHING_TOPIC = "SST/desktopTwo/id";
    Activity mContext;
    // MQTT objects
    MqttAndroidClient mqttAndroidClient = null;
    MQTTServerClient mqttServerClient = null;

    @BindView(R.id.tvMqtts)
    TextView tvMqtt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_deviceconfig, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        getInitialConfig();
        return rootView;
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

    @Override
    public void onSuccess(MqttAndroidClient _mqttAndroidClient) {
        try {
            mqttAndroidClient = _mqttAndroidClient;
            Log.e(MQTT_CONNECTION, "Connected");
            if (mqttAndroidClient != null) {
                mqttServerClient.subscribe(mqttAndroidClient, LIVE_STATUS_TOPIC);
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
            tvMqtt.setText(response);
           // byte[] responseBody = Utils.hexStringToByteArray(response);
           // request.mergeFrom(responseBody);
            Log.i("MQTT Response", response);

            try {

                JSONObject obj = new JSONObject(response);

                Log.d("My App", obj.toString());
              //  Log.d("My App"+ );
                tvMqtt.setText(obj.get("id").toString());

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
            mqttServerClient.subscribe(mqttAndroidClient, LIVE_STATUS_TOPIC);
        }
    }
}
