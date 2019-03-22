package producttracking.iexemplar.com.mqtt;

import android.util.Log;


import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import producttracking.iexemplar.com.app.ApplicationContext;


public class MQTTActionListener implements IMqttActionListener, ApplicationContext {

    MqttAndroidClient mqttAndroidClient;
    private ServerCallBack serverCallBack;

    public MQTTActionListener(MqttAndroidClient client, ServerCallBack callback) {
        this.mqttAndroidClient = client;
        this.serverCallBack = callback;
    }

    /**
     * This method is invoked when an action has completed successfully.
     *
     * @param asyncActionToken associated with the action that has completed
     */
    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.i(MQTT_CONNECTION, MQTT_CONNECTION_SUCCESS);
        serverCallBack.onSuccess(mqttAndroidClient);
    }

    /**
     * This method is invoked when an action fails.
     * If a client is disconnected while an action is in progress
     * onFailure will be called. For connections
     * that use cleanSession set to false, any QoS 1 and 2 messages that
     * are in the process of being delivered will be delivered to the requested
     * quality of service next time the client connects.
     *
     * @param asyncActionToken associated with the action that has failed
     * @param exception
     */
    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.e(MQTT_CONNECTION, MQTT_CONNECTION_FAILED);
        serverCallBack.onFailure(exception.toString());
    }
}
