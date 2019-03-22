/**
 *
 */
package producttracking.iexemplar.com.mqtt;

import android.app.Activity;
import android.provider.Settings;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

import producttracking.iexemplar.com.BuildConfig;
import producttracking.iexemplar.com.app.ApplicationContext;

public class MQTTServerClient implements ApplicationContext {

    private String CONNECTION_KEY = "epms123456";
    private String CA_CERT = "ca-cert.pem";
    private String CLIENT_CERT = "client-cert.pem";
    private String CLIENT_KEY = "client-key.pem";
    private int TIME_OUT = 10;
    private int INTERVAL = 20;

    private ServerCallBack serverCallBack;

    public void mqttCallBack(ServerCallBack callback) {
        serverCallBack = callback;
    }


    public void clientConnect(Activity activity) {
        String clientId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        MqttAndroidClient client = new MqttAndroidClient(activity, MQTT_SERVER_ENDPOINT,
                clientId);
        try {
//            InputStream caFilePath = activity.getAssets().open(CA_CERT);
//            InputStream clientCrtFilePath = activity.getAssets().open(CLIENT_CERT);
//            InputStream clientKeyFilePath = activity.getAssets().open(CLIENT_KEY);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            options.setConnectionTimeout(TIME_OUT);
            options.setAutomaticReconnect(true);
            //options.setSocketFactory(SSLFactory.getSocketFactory(caFilePath, clientCrtFilePath, clientKeyFilePath, CONNECTION_KEY));
            IMqttToken token = client.connect(options);
            token.setActionCallback(new MQTTActionListener(client, serverCallBack));
        }
//        catch (IOException e) {
//            Log.d(MQTT_CONNECTION, e.toString());
//            serverCallBack.connectionLost(e.toString());
//        }
        catch (NullPointerException e) {
            Log.d(MQTT_CONNECTION, e.toString());
            serverCallBack.connectionLost(e.toString());
        } catch (MqttException e) {
            Log.d(MQTT_CONNECTION, e.toString());
            serverCallBack.connectionLost(e.toString());
        } catch (Exception e) {
            Log.d(MQTT_CONNECTION, e.toString());
            serverCallBack.connectionLost(e.toString());
        }
    }


    public void subscribe(MqttAndroidClient mqttAndroidClient, final String topic) {
        Log.i("SUBSCRIBE", topic);
        int qos = 1;
        try {
            mqttAndroidClient.subscribe(topic, qos);
            mqttAndroidClient.setCallback(new MQTTServerCallBack(mqttAndroidClient, serverCallBack));
        } catch (MqttException e) {
            serverCallBack.connectionLost(e.toString());
        } catch (Exception e) {
            serverCallBack.connectionLost(e.toString());
        }
    }

    public void unsubscribe(MqttAndroidClient mqttAndroidClient, final String topic) {
        Log.i("UNSUBSCRIBE", topic);
        try {
            mqttAndroidClient.unsubscribe(topic);
            mqttAndroidClient.setCallback(new MQTTServerCallBack(mqttAndroidClient, serverCallBack));
        } catch (MqttException e) {
            serverCallBack.connectionLost(e.toString());
        } catch (Exception e) {
            serverCallBack.connectionLost(e.toString());
        }
    }

    public void publish(MqttAndroidClient mqttAndroidClient, final String topic, String body) {
        Log.i("PUBLISH", topic);
        Log.i("PAYLOAD", body);
        int qos = 1;
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = body.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            message.setRetained(false);
            mqttAndroidClient.publish(topic, message);
            mqttAndroidClient.setCallback(new MQTTServerCallBack(mqttAndroidClient, serverCallBack));
        } catch (MqttException e) {
            serverCallBack.connectionLost(e.toString());
        } catch (UnsupportedEncodingException e) {
            serverCallBack.connectionLost(e.toString());
        }
    }

}
