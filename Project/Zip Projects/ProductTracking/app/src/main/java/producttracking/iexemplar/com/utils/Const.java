package producttracking.iexemplar.com.utils;

import okhttp3.MediaType;

public interface Const {

    MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String EXCEPTION = "Exception";
    String JSONEXCEPTION = "JSONException";

    String baseUrl = "http://172.20.10.9:9090/api/v1/";
    String baseJSONUrl = "http://172.20.10.9:8000/";

}
