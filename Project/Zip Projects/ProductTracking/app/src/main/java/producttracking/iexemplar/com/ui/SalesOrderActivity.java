package producttracking.iexemplar.com.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.app.AppBaseActivity;
import producttracking.iexemplar.com.app.ApplicationContext;
import producttracking.iexemplar.com.service.APIInterface;
import producttracking.iexemplar.com.service.JSONRequest;
import producttracking.iexemplar.com.service.RetrofitClient;
import producttracking.iexemplar.com.service.model.SalesOrder;
import producttracking.iexemplar.com.service.model.SalesOrderPost;
import producttracking.iexemplar.com.service.model.TowelDetail;
import producttracking.iexemplar.com.utils.ConnectionDetector;
import producttracking.iexemplar.com.utils.Conversion;
import producttracking.iexemplar.com.utils.DialogUtil;
import producttracking.iexemplar.com.utils.ProgressUtil;
import producttracking.iexemplar.com.utils.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesOrderActivity extends AppBaseActivity implements ApplicationContext {

    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.toolbar_title)
    TextView tvTitile;

    @BindView(R.id.btn_submit)
    Button btnSubmitSlsOrder;

    @BindView(R.id.et_sal_ord_num)
    EditText etOrderNumber;

    @BindView(R.id.et_sal_ord_date)
    EditText etOrderDate;

    @BindView(R.id.et_sal_ord_cusname)
    EditText etCusName;

    @BindView(R.id.et_sal_ord_country)
    EditText etOrderCountry;

    @BindView(R.id.et_sal_ord_twl_name)
    EditText etTowelName;

    @BindView(R.id.et_sal_ord_twl_clr)
    EditText etTowelclr;

    @BindView(R.id.et_sal_ord_twl_qty)
    EditText etTowelQty;


    @BindView(R.id.btn_cancel)
    Button btnCancel;


    SimpleDateFormat simpleDateFormat;
    String FORMAT_DATE_YYYYMMDD = "yyyy-MM-dd";
    int REQUEST_CATEGORY = 1000;
    int year, month, date, pYear, pMonth, pDate;
    int STATIC_YEAR = 1900;

    ConnectionDetector connectionDetector;
    ProgressDialog mProgressDialog;
    APIInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesorder);
        ButterKnife.bind(this);
        getObjectConfiguration();
        registerBaseActivityReceiver();
    }


    private void getObjectConfiguration() {
//        String mDepartName = getIntent().getStringExtra(INTENT_DEPARTMENT_NAME);
//        controllerId = getIntent().getStringExtra(INTENT_CONTROLLER_ID);
//        tvMachineName.setText(machineName);
//        departName.setText(mDepartName);
//        serviceConnector = new ServiceConnector();
//        serviceConnector.registerCallback(this);
//        connectionDetector = new ConnectionDetector(this);
        connectionDetector = new ConnectionDetector(this);
        //apiInterface = RetrofitClient.getClient();
        apiInterface = RetrofitClient.getClientJson();
        ivBack.setImageResource(R.drawable.back_button);
        ivBack.setVisibility(View.VISIBLE);
        tvTitile.setText(R.string.header_sales_order);
        setDate();
    }

    @OnClick(R.id.btn_submit)
    public void submitSalesOrder(View view) {
        if (Validator.isEmptyField(etOrderNumber) && Validator.isEmptyField(etOrderDate) && Validator.isEmptyField(etCusName)) {
            new DialogUtil(SalesOrderActivity.this, getResources().getString(R.string.warning_empty_allfields)).show();
            return;
        }
        boolean isConnected = connectionDetector.isConnectingToInternet();
        if (isConnected) {
            mProgressDialog = ProgressUtil.showDialog(this);
            String ordername = etOrderNumber.getText().toString().trim();
            String orderdate = etOrderDate.getText().toString();
            String cusname = etCusName.getText().toString().trim();
            String country = etOrderCountry.getText().toString().trim();
            String twlname = etTowelName.getText().toString().trim();
            String twlclr = etTowelclr.getText().toString().trim();
            String twlqty = etTowelQty.getText().toString().trim();

            SalesOrderPost salesOrderPost = new SalesOrderPost();
            salesOrderPost.setCountry(country);
            salesOrderPost.setCustomerName(cusname);
            salesOrderPost.setDate(orderdate);
            salesOrderPost.setNumber(ordername);

            List<TowelDetail> towelDetailList = new ArrayList<>();

            TowelDetail towelDetail = new TowelDetail();
            towelDetail.setTowelName(twlname);
            towelDetail.setColour(twlclr);
            towelDetail.setQuantity(twlqty);
            towelDetailList.add(towelDetail);

            towelDetail = new TowelDetail();
            towelDetail.setTowelName("Siraj");
            towelDetail.setColour("Red");
            towelDetail.setQuantity("32kg");
            towelDetailList.add(towelDetail);

            salesOrderPost.setTowelDetailsList(towelDetailList);


            RequestBody body = JSONRequest.requestSalesOrder(ordername, orderdate, cusname, country, "[]");
            Log.e("", "body: " + body);
            apiInterface.salesorder(salesOrderPost).enqueue(new Callback<List<SalesOrder>>() {
                @Override
                public void onResponse(Call<List<SalesOrder>> call, Response<List<SalesOrder>> response) {
                    Log.e("results sales order", "onResponseSalesOrder: " + response.code());
                    mProgressDialog.dismiss();
                    if (response.code() == 201) {
                        new DialogUtil(SalesOrderActivity.this, "Sales Order Raised Successfully").show();
                    } else {
                        new DialogUtil(SalesOrderActivity.this, "Failed to Raise Sales Order").show();
                    }
                }

                @Override
                public void onFailure(Call<List<SalesOrder>> call, Throwable t) {
                    mProgressDialog.dismiss();
                    new DialogUtil(SalesOrderActivity.this, "Sales Order Raised Successfully").show();
                }
            });
            //apiInterface.login(body).enqueue(new Callback<User>() {
            //JSONArray jsonKeyValue = new JSONArray();
            //  JSONArray jsonKeys = new JSONArray();
            // jsonKeys = Conversion.getKeyValuesArray(jsonKeys, "towelName", twlname);
            //  System.out.print(jsonKeys);
            // jsonKeyValue.put("towerDetails",jsonKeys);

        } else {
            new DialogUtil(this, getResources().getString(R.string.no_internet_connection)).show();
        }
    }

    private void setDate() {
        simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYYMMDD, Locale.getDefault());
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        date = now.get(Calendar.DAY_OF_MONTH);
    }

    @OnClick(R.id.et_sal_ord_date)
    public void onClick(View view) {
        Conversion.keyboardDisable(this, view);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.datepickerCustom, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                try {
                    String dateofPurchase = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    Date date = simpleDateFormat.parse(dateofPurchase);
                    etOrderDate.setText(simpleDateFormat.format(date));
                    pYear = year;
                    pMonth = monthOfYear;
                    pDate = dayOfMonth + 1;
                } catch (ParseException exception) {
                    Log.e("", exception.toString());
                }

            }
        }, year, month, date);
        Calendar cal = Calendar.getInstance();
        cal.set(STATIC_YEAR, 01, 01);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @OnClick(R.id.iv_back)
    public void backlView() {
        finish();
    }

    @OnClick(R.id.btn_cancel)
    public void cancelView() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBaseActivityReceiver();
    }
}
