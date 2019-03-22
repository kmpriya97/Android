//package producttracking.iexemplar.com.utils;
//
///*******************************************************************************
// * Created by iExemplar on 11/8/2017.
// * <p>
// * Copyright (c) 2017 iExemplar. All rights reserved.
// *******************************************************************************/
//
//import android.app.Activity;
//import android.content.Context;
//import android.provider.Settings;
//import android.util.Log;
//
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//import java.util.TimeZone;
//
//
//
//public class Utils {
//
//    private static String FORMAT_CFM_DATA = "%.2f";
//
//    /************************************************************************************
//     * Class      : Utility
//     * Use        : Method Call convert md5 Format
//     * Created on : 11/10/2017
//     * Updated on : 11/10/2017
//     * Created By : iExemplar Software India Pvt Ltd.
//     **************************************************************************************/
//    public static String md5Format(String s) {
//        StringBuffer sb = null;
//        try {
//            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
//            byte[] array = md.digest(s.getBytes());
//            sb = new StringBuffer();
//            for (byte anArray : array) {
//                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
//            }
//        } catch (java.security.NoSuchAlgorithmException e) {
//            Log.e(EXCEPTION,e.toString());
//        }
//        assert sb != null;
//        return sb.toString();
//    }
//
//
//    /************************************************************************************
//     * Class      : Utility
//     * Use        : Method Call device Id
//     * Created on : 11/10/2017
//     * Updated on : 11/10/2017
//     * Created By : iExemplar Software India Pvt Ltd.
//     **************************************************************************************/
//    public static String deviceId(Context ctx) {
//        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
//    }
//
//
//    /************************************************************************************
//     * Class      : Utility
//     * Use        : Method Call Firebase Registered Id
//     * Created on : 11/10/2017
//     * Updated on : 11/10/2017
//     * Created By : iExemplar Software India Pvt Ltd.
//     **************************************************************************************/
//    public static String regId(Context ctx) {
//        return PreferenceConnector.readString(ctx, PreferenceConnector.REG_ID, "regId");
//    }
//
//    /************************************************************************************
//     * Class      : Utility
//     * Use        : Method Call Alert Dialog
//     * Created on : 11/10/2017
//     * Updated on : 11/10/2017
//     * Created By : iExemplar Software India Pvt Ltd.
//     **************************************************************************************/
//    public static void alertDialog(Activity ctx, String title, String message) {
//        if (title.equals("Warning")) {
//            new OneButtonDialog(ctx)
//                    .setTitle(title)
//                    .setMessage(message)
//                    .setColoredCircle(R.color.colorPrimary)
//                    .setDialogIconAndColor(R.drawable.ic_dialog_warning, R.color.white)
//                    .setCancelable(false)
//                    .setButtonText("OK")
//                    .setButtonBackgroundColor(R.color.colorPrimary)
//                    .setButtonTextColor(R.color.white)
//                    .setWarningButtonClick(new Closure() {
//                        @Override
//                        public void exec() {
//                            // click
//                        }
//                    })
//                    .show();
//        } else {
//            new OneButtonDialog(ctx)
//                    .setTitle(title)
//                    .setMessage(message)
//                    .setColoredCircle(R.color.colorPrimary)
//                    .setDialogIconAndColor(R.drawable.ic_dialog_success, R.color.white)
//                    .setCancelable(false)
//                    .setButtonText("OK")
//                    .setButtonBackgroundColor(R.color.colorPrimary)
//                    .setButtonTextColor(R.color.white)
//                    .setWarningButtonClick(new Closure() {
//                        @Override
//                        public void exec() {
//                            // click
//                        }
//                    })
//                    .show();
//        }
//    }
//
//
//    /************************************************************************************
//     * Class      : Utility
//     * Use        : Method Call for Api Error Parsing
//     * Created on : 11/10/2017
//     * Updated on : 11/10/2017
//     * Created By : iExemplar Software India Pvt Ltd.
//     **************************************************************************************/
//    public static Boolean successParsing(int statusCode) {
//        boolean isSuccess = false;
//        switch (statusCode) {
//            case 200:
//                isSuccess = true;
//                break;
//            case 201:
//                isSuccess = true;
//                break;
//            case 202:
//                isSuccess = true;
//                break;
//            default:
//                isSuccess = false;
//        }
//        return isSuccess;
//    }
//
//    public static Boolean checkEmpty(String response) {
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            JSONArray jsonObjectError = new JSONArray(jsonObject.getString("results"));
//            return jsonObjectError.length() != 0;
//        } catch (JSONException jsonException) {
//            return false;
//        }
//    }
//
//
//    /************************************************************************************
//     * Class      : Utility
//     * Use        : Method Call for Api Error Parsing
//     * Created on : 11/10/2017
//     * Updated on : 11/10/2017
//     * Created By : iExemplar Software India Pvt Ltd.
//     **************************************************************************************/
//    public static void errorParsing(Activity ctx, String response, int statusCode) {
//        switch (statusCode) {
//            case 304:
//                errorObject(ctx, response);
//                break;
//            case 400:
//                errorObject(ctx, response);
//                break;
//            case 401:
//                errorObject(ctx, response);
//                break;
//            case 403:
//                errorObject(ctx, response);
//                break;
//            case 404:
//                errorArray(ctx, response);
//                break;
//            case 405:
//                errorArray(ctx, response);
//                break;
//            case 406:
//                errorArray(ctx, response);
//                break;
//            case 409:
//                errorObject(ctx, response);
//                break;
//            case 412:
//                errorArray(ctx, response);
//                break;
//            case 415:
//                errorArray(ctx, response);
//                break;
//            case 440:
//                errorArray(ctx, response);
//                break;
//            case 500:
//                errorArray(ctx, response);
//                break;
//            default:
//                errorObject(ctx, response);
//                break;
//        }
//    }
//
//    private static void errorObject(Activity ctx, String response) {
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            JSONObject jsonObjectError = new JSONObject(jsonObject.getString("errors"));
//            Utils.alertDialog(ctx, ctx.getString(R.string.warning), jsonObjectError.getString("message"));
//        } catch (JSONException jsonException) {
//            try {
//                JSONObject jsonObject = new JSONObject(response);
//                JSONArray jsonObjectError = new JSONArray(jsonObject.getString("errors"));
//                Utils.alertDialog(ctx, ctx.getString(R.string.warning), jsonObjectError.getString(0));
//            } catch (JSONException jsonException1) {
//                Utils.alertDialog(ctx, ctx.getString(R.string.warning), jsonException.getMessage());
//            }
//        }
//    }
//
//    public static String parsingKeyValues(JSONArray jsonArray, int indexPos) {
//        String valString = "";
//        try {
//            JSONObject jsonObjectPurchaseDate = jsonArray.getJSONObject(indexPos);
//            jsonArray = new JSONArray(jsonObjectPurchaseDate.getString("values"));
//            if (jsonArray.length() > 0) {
//                valString = jsonArray.getString(0);
//            }
//        } catch (JSONException jsonException) {
//            Log.i("KeyParsing", jsonException.getMessage());
//        }
//        return valString;
//    }
//
//    private static void errorArray(Activity ctx, String response) {
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            JSONArray jsonObjectError = new JSONArray(jsonObject.getString("errors"));
//            Utils.alertDialog(ctx, ctx.getString(R.string.warning), jsonObjectError.getString(0));
//        } catch (JSONException jsonException) {
//            try {
//                JSONObject jsonObject = new JSONObject(response);
//                JSONObject jsonObjectError = new JSONObject(jsonObject.getString("errors"));
//                Utils.alertDialog(ctx, ctx.getString(R.string.warning), jsonObjectError.getString("message"));
//            } catch (JSONException jsonException1) {
//                Utils.alertDialog(ctx, ctx.getString(R.string.warning), jsonException.getMessage());
//            }
//
//        }
//    }
//
//
//    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
//
//    public static String bytesToHex(byte[] bytes) {
//        char[] hexChars = new char[bytes.length * 2];
//        for (int j = 0; j < bytes.length; j++) {
//            int v = bytes[j] & 0xFF;
//            hexChars[j * 2] = hexArray[v >>> 4];
//            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
//        }
//        return new String(hexChars);
//    }
//
//
//    public static byte[] hexStringToByteArray(String response) {
//        int len = response.length();
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) ((Character.digit(response.charAt(i), 16) << 4)
//                    + Character.digit(response.charAt(i + 1), 16));
//        }
//        return data;
//    }
//
//    public static String addDigitLatLongFormat(String actualString) {
//        if (actualString.equals("")) {
//            actualString = "00.00";
//        }
//        try {
//            float zoneFormat = Float.parseFloat(actualString);
//            DecimalFormat numberFormat = new DecimalFormat("00.00");
//            actualString = numberFormat.format(zoneFormat);
//        } catch (NumberFormatException mExccepton) {
//            actualString = "00.00";
//        }
//        return actualString;
//    }
//
//
//    public static JSONArray getKeyValues(JSONArray jsonKeys, String key, String value) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(KEY, key);
//            jsonObject.put(VALUES, new JSONArray().put(value));
//            if (!value.equals("")) {
//                jsonKeys.put(jsonObject);
//            }
//        } catch (JSONException jsonException) {
//
//        }
//        return jsonKeys;
//    }
//
//
//    /************************************************************************************
//     * Class      : Utils
//     * Use        : Method Call All formatting time
//     * Created on : 11/20/2017
//     * Updated on : 11/21/2017
//     * Created By : iExemplar Software India Pvt Ltd.
//     **************************************************************************************/
//    public static String formatTime(Date date) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        String formattedTime = "";
//        try {
//            return formatter.format(date).toUpperCase();
//        } catch (NumberFormatException e) {
//            Log.e("Hour Error", e.toString());
//            return formattedTime;
//        }
//    }
//
//
//     public static String addValue(int position, String key, String address) {
//        StringBuilder stringBuffer = new StringBuilder();
//        stringBuffer.append(address);
//        if (key.trim().length() != 0) {
//            if (position == 1) {
//                stringBuffer.append(key);
//            } else {
//                stringBuffer.append(", ").append(key);
//            }
//        }
//        return stringBuffer.toString();
//    }
//
//
//    public static String convertLocalTime(Context ctx, String sourceDateTime) {
//        String convertedTime = "";
//        try {
//            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//            Date parsed = sourceFormat.parse(sourceDateTime); // => Date is in UTC now
//
//            String timeZoneId = PreferenceConnector.readString(ctx, PreferenceConnector.TIME_ZONE_ID, "Asia/Kolkata");
//            Log.i("Local Time Zone", timeZoneId);
//            Log.i("Before Time", sourceFormat.format(parsed));
//            TimeZone tz = TimeZone.getTimeZone(timeZoneId);
//            SimpleDateFormat destFormat = new SimpleDateFormat("hh:mm a");
//            destFormat.setTimeZone(tz);
//
//            convertedTime = destFormat.format(parsed).toUpperCase();
//            Log.i("After Time", convertedTime);
//        } catch (Exception e) {
//            Log.e("convertLocalTime : ", e.toString());
//        }
//        return convertedTime;
//    }
//
//
//    public static String convertLocalDate(Context ctx, String sourceDateTime) {
//        String convertedTime = "";
//        try {
//            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//            Date parsed = sourceFormat.parse(sourceDateTime); // => Date is in UTC now
//
//            String timeZoneId = PreferenceConnector.readString(ctx, PreferenceConnector.TIME_ZONE_ID, "Asia/Kolkata");
//            Log.i("Before Date", sourceFormat.format(parsed));
//            TimeZone tz = TimeZone.getTimeZone(timeZoneId);
//            SimpleDateFormat destFormat = new SimpleDateFormat("dd-MM-yyyy");
//            destFormat.setTimeZone(tz);
//
//            convertedTime = destFormat.format(parsed);
//            Log.i("After Date", convertedTime);
//        } catch (Exception e) {
//            Log.e("convertLocalDate : ", e.toString());
//        }
//        return convertedTime;
//    }
//
//
//    public static String convertLocalDisplayDate(Context ctx, String sourceDateTime) {
//        String convertedTime = "";
//        try {
//            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sourceFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//            Date parsed = sourceFormat.parse(sourceDateTime); // => Date is in UTC now
//
//            String timeZoneId = PreferenceConnector.readString(ctx, PreferenceConnector.TIME_ZONE_ID, "Asia/Kolkata");
//            Log.i("Before Date", sourceFormat.format(parsed));
//            TimeZone tz = TimeZone.getTimeZone(timeZoneId);
//            SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd");
//            destFormat.setTimeZone(tz);
//
//            convertedTime = destFormat.format(parsed);
//            Log.i("After Date", convertedTime);
//        } catch (Exception e) {
//            Log.e("convertLocalDate : ", e.toString());
//        }
//        return convertedTime;
//    }
//
//
//    public static String getAverageCaluateCFM(double sytemAvg, int count) {
//        return String.format(FORMAT_CFM_DATA, (sytemAvg / count));
//    }
//
//    public static String getAverageCaluateCFM(float sytemAvg, int count) {
//        return String.format(FORMAT_CFM_DATA, (sytemAvg / count));
//    }
//
//
//    public static String formatCFM(String machineAverage) {
//        return String.format(FORMAT_CFM_DATA, Float.valueOf(machineAverage));
//    }
//}
