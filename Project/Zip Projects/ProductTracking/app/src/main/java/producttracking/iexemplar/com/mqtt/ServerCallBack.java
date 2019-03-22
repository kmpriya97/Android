package producttracking.iexemplar.com.mqtt;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public interface ServerCallBack {

    void onSuccess(MqttAndroidClient mqttAndroidClient);

    void onFailure(String message);

    void onMessageReceived(String topic, MqttMessage mqttMessage);

    void connectionLost(String error);

    void reconneted(MqttAndroidClient mqttAndroidClient);


}
