package producttracking.iexemplar.com.service;

import android.util.Log;

import producttracking.iexemplar.com.utils.Const;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;


public class JSONRequest implements Const {


   public static RequestBody requestSignIn(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonKeys = new JSONArray();
            jsonObject.put(ApiKeys.USER_NAME, email);
            jsonObject.put(ApiKeys.PASSWORD, password);
        } catch (JSONException jsonException) {
            Log.e(JSONEXCEPTION, jsonException.getMessage());
        }
        return RequestBody.create(JSON, jsonObject.toString());
    }

    public static RequestBody requestSalesOrder(String num, String date, String name, String country, String toweldetails) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonKeys = new JSONArray();
            jsonObject.put(ApiKeys.ORD_NUMBER, num);
            jsonObject.put(ApiKeys.ORD_DATE, date);
            jsonObject.put(ApiKeys.ORD_CUS_NAME, name);
            jsonObject.put(ApiKeys.ORD_COUNTRY, country);
            jsonObject.put(ApiKeys.ORD_TOWEL,toweldetails);
        } catch (JSONException jsonException) {
            Log.e(JSONEXCEPTION, jsonException.getMessage());
        }
        return RequestBody.create(JSON, jsonObject.toString());
    }

}
