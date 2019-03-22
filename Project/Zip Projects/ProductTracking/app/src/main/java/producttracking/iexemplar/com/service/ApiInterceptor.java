package producttracking.iexemplar.com.service;

import android.support.annotation.NonNull;
import android.util.Log;
import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_ACCEPT = "Accept";


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request chainRequest = chain.request();
        Request.Builder builder = chainRequest.newBuilder();
        builder.header(HEADER_CONTENT_TYPE, "application/json");
        builder.header(HEADER_ACCEPT, "application/json");
        Request request = builder.build();
        return chain.proceed(request);
    }
}
