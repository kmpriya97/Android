package producttracking.iexemplar.com.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Conversion implements Const{
    public static String md5Format(String s) {
        StringBuffer sb = null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(s.getBytes());
            sb = new StringBuffer();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            Log.e(EXCEPTION,e.toString());
        }
        assert sb != null;
        return sb.toString();
    }

    public static Boolean checkEmpty(String response) {
        try {
            Log.e("checkEmpty", "checkEmpty: "+response);
            JSONObject jsonObject = new JSONObject(response);
            Log.e("checkEmpty", "checkEmpty: " + jsonObject);
            JSONArray jsonObjectError = new JSONArray(response);
            Log.e("jsonObjectError", "jsonObjectError: " + jsonObjectError);
            Log.e("Length","Lenght is "+jsonObjectError.length());
            return jsonObjectError.length() != 0;
        } catch (JSONException jsonException) {
            return false;
        }
    }

    public static Boolean successParsing(int statusCode) {
        boolean isSuccess = false;
        switch (statusCode) {
            case 200:
                isSuccess = true;
                break;
            case 201:
                isSuccess = true;
                break;
            case 202:
                isSuccess = true;
                break;
            default:
                isSuccess = false;
        }
        return isSuccess;
    }

    public static void keyboardDisable(Activity mActivity, View v) {
        InputMethodManager iManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (iManager.isAcceptingText()) {
            iManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static String getKeyValues(String key, String value) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key, value);
        } catch (JSONException jsonException) {

        }
        return jsonObject.toString();
    }

    public static JSONArray getKeyValuesArray(JSONArray jsonKeys, String key, String value) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(key, value);
            jsonKeys.put(jsonObject);
        } catch (JSONException jsonException) {
            Log.e("", "getKeyValuesArray: error");
        }
        return jsonKeys;
    }

}
