package producttracking.iexemplar.com.service;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import producttracking.iexemplar.com.service.model.Indent;
import producttracking.iexemplar.com.service.model.ProcessStatus;
import producttracking.iexemplar.com.service.model.SalesOrder;
import producttracking.iexemplar.com.service.model.SalesOrderPost;
import producttracking.iexemplar.com.service.model.TowelStatus;
import producttracking.iexemplar.com.service.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @POST(ApiEndPoints.SESSION)
    Call<User> login(@Body RequestBody body);

    @GET(ApiEndPoints.SALESORDER)
    Call<List<SalesOrder>> getSalesOrderList();


    @POST(ApiEndPoints.SALESORDER)
    Call<List<SalesOrder>> salesorder(@Body SalesOrderPost salesOrderPost);

    @GET(ApiEndPoints.STATUS)
    Call<ProcessStatus> checkStatus(@Query(ApiKeys.INDENT_NAME) String indentName,
                                    @Query(ApiKeys.PROCESS_NAME) String processName);

    @GET(ApiEndPoints.INDENT)
    Call<List<Indent>> getIndentList();

    @GET(ApiEndPoints.CONFECTION)
    Call<TowelStatus> getCrossCut(@Query(ApiKeys.INDENT_NAME) String indentName);

    @GET(ApiEndPoints.STICH)
    Call<TowelStatus> getStichCut(@Query(ApiKeys.INDENT_NAME) String indentName);

}
