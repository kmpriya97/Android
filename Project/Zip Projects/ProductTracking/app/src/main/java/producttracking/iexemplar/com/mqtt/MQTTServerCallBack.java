/**
 *
 */
package producttracking.iexemplar.com.mqtt;

import android.util.Log;


import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import producttracking.iexemplar.com.app.ApplicationContext;


public class MQTTServerCallBack implements MqttCallbackExtended, ApplicationContext {

    MqttAndroidClient mqttAndroidClient;
    private ServerCallBack serverCallBack;

    public MQTTServerCallBack(MqttAndroidClient mqttAndroidClient, ServerCallBack serverCallBack) {
        this.mqttAndroidClient = mqttAndroidClient;
        this.serverCallBack = serverCallBack;
    }

    public void connectionLost(Throwable throwable) {
        Log.e(MQTT_EXCEPTION, throwable.toString());
        serverCallBack.connectionLost(throwable.toString());

    }

    public void messageArrived(String topic, MqttMessage mqttMessage) {
        Log.i(MQTT_MSG, new String(mqttMessage.getPayload()));
        serverCallBack.onMessageReceived(topic, mqttMessage);

    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        Log.i(MQTT_DELIVERY, iMqttDeliveryToken.toString());
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        if (reconnect) {
            serverCallBack.reconneted(mqttAndroidClient);
            Log.i(MQTT_CONNECTION, MQTT_CONNECTION_RECONNECT);
        } else {
            Log.i(MQTT_CONNECTION, MQTT_CONNECTION_SUCCESS);
        }

    }
}
