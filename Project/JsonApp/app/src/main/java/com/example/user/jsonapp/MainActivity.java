package com.example.user.jsonapp;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etJob;
    Button btPut;
    Button btPost;
    Button btGet;
    Button btDelete;
    String name;
    String job;
    TextView content;
    String myUrl = "https://reqres.in/api/users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        content = findViewById( R.id.content );
        etName = findViewById( R.id.et_name );
        etJob = findViewById( R.id.et_job );
        btDelete = findViewById( R.id.bt_delete );
        btGet = findViewById( R.id.bt_get );
        btPost = findViewById( R.id.bt_post );
        btPut = findViewById( R.id.bt_put );
        btPost.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendPostRequest().execute();
            }
        } );
    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL( "https://reqres.in/api/users" ); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put( "name", etName.getText().toString() );
                postDataParams.put( "job", etJob.getText().toString() );
                Log.e( "params", postDataParams.toString() );

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout( 15000 /* milliseconds */ );
                conn.setConnectTimeout( 15000 /* milliseconds */ );
                conn.setRequestMethod( "POST" );
                conn.setDoInput( true );
                conn.setDoOutput( true );

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter( os, "UTF-8" ) );
                writer.write( getPostDataString( postDataParams ) );

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                // if (responseCode == HttpsURLConnection.HTTP_OK) {
                if ((responseCode == 200) || (responseCode == 201)){
                    BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );

                StringBuffer sb = new StringBuffer( "" );
                String line = "";

                while ((line = in.readLine()) != null) {

                    sb.append( line );
                    break;
                }

                in.close();
                return sb.toString();

            } else{
                return new String( "false : " + responseCode );
            }
        } catch(
        Exception e)

        {
            return new String( "Exception: " + e.getMessage() );
        }

    }

    @Override
    protected void onPostExecute(String result) {
        // Toast.makeText( getApplicationContext(), result, Toast.LENGTH_LONG ).show();
        content.setText( result );
    }

}

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get( key );

            if (first)
                first = false;
            else
                result.append( "&" );

            result.append( URLEncoder.encode( key, "UTF-8" ) );
            result.append( "=" );
            result.append( URLEncoder.encode( value.toString(), "UTF-8" ) );

        }
        return result.toString();
    }
}























    /*@SuppressLint("StaticFieldLeak")
    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {

                try {
                    URL url = new URL( myUrl );
                    String result="";

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod( "POST" );
                    conn.setRequestProperty( "Content-Type", "application/json; charset=utf-8" );
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate( "name", etName.getText().toString() );
                    jsonObject.accumulate( "job", etJob.getText().toString() );

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( os, "UTF-8" ) );
                    writer.write( jsonObject.toString() );
                    //result = jsonObject.toString();
                    //Log.i( "result is", result );
                    Log.i( MainActivity.class.toString(), jsonObject.toString() );
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();
                    int code = conn.getResponseCode();

                        if ((code == 200) || (code == 201)) {
                            // String response = conn.getResponseMessage();

                            // Log.i( "", "response created: " + response );
                            //return response;
                            return conn.getResponseMessage() + "Success!!!";
                        } else
                            return "Server Error!! Please try again!!!";


                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }


        @Override
        protected void onPostExecute(String result) {
            content.setText( result );
        }
    }
}*/
