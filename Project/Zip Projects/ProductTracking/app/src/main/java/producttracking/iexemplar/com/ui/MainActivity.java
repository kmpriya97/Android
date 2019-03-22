package producttracking.iexemplar.com.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.RequestBody;
import producttracking.iexemplar.com.R;
import producttracking.iexemplar.com.app.AppBaseActivity;
import producttracking.iexemplar.com.service.JSONRequest;
import producttracking.iexemplar.com.service.RetrofitClient;
import producttracking.iexemplar.com.service.model.Datum;
import producttracking.iexemplar.com.service.model.User;
import producttracking.iexemplar.com.utils.DialogUtil;
import producttracking.iexemplar.com.utils.Validator;
import producttracking.iexemplar.com.utils.ConnectionDetector;
import producttracking.iexemplar.com.utils.ProgressUtil;
import producttracking.iexemplar.com.utils.Conversion;
import producttracking.iexemplar.com.service.APIInterface;
import producttracking.iexemplar.com.preference.PreferenceConnector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static producttracking.iexemplar.com.app.ApplicationContext.INTENT_POSITION;

public class MainActivity extends AppBaseActivity {

    @BindView(R.id.et_user_id)
    EditText etUserId;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_continue)
    Button btnLogin;

    private Unbinder viewUnBinder;

//    @Inject
//    @ApplicationContext
//    public Context mContext;

    ConnectionDetector connectionDetector;
    ProgressDialog mProgressDialog;
    APIInterface apiInterface;

    //ArrayList<Machine> machineArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewUnBinder = ButterKnife.bind(this);
        getInitialSetupLogin();
        registerBaseActivityReceiver();
    }

    private void getInitialSetupLogin() {
        connectionDetector = new ConnectionDetector(this);
        apiInterface = RetrofitClient.getClient();
        etUserId.setText("ixmuser@gmail.com");
        etPassword.setText("123456");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBaseActivityReceiver();
    }

    @OnClick(R.id.btn_continue)
    public void btnContinue(View view) {


        if (Validator.isEmptyField(etUserId) && Validator.isEmptyField(etPassword)) {
            new DialogUtil(MainActivity.this,getResources().getString(R.string.warning_empty_allfields)).show();
        } else if (Validator.isEmptyField(etUserId)) {
            new DialogUtil(MainActivity.this,getResources().getString(R.string.email_error)).show();
        } else if (Validator.isEmptyField(etPassword)) {
            new DialogUtil(MainActivity.this,getResources().getString(R.string.pwd_error)).show();
        } else if (!Validator.isEmailValid(etUserId.getText().toString())) {
            new DialogUtil(MainActivity.this,getResources().getString(R.string.warning_invalid_email)).show();
        } else if (Validator.isValidPassword(etPassword)) {
            new DialogUtil(MainActivity.this,getResources().getString(R.string.warning_incorrect_password)).show();
        } else {
            boolean isConnected = connectionDetector.isConnectingToInternet();
            if (isConnected) {
                String email = etUserId.getText().toString().trim();
                String password = Conversion.md5Format(etPassword.getText().toString());
                mProgressDialog = ProgressUtil.showDialog(MainActivity.this);
                RequestBody body = JSONRequest.requestSignIn(email, password);
                Log.i("", "body: "+body);
                apiInterface.login(body).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        Log.e("results", "onResponseLogin: "+response.code());
                        if (response.code() == 200) {
                            if (response.body().getCode() == 200) {
                                Datum datum = response.body().getData().get(0);
                                Log.e("test", "onResponse: userId "+datum.getUserId());
                                Log.e("test", "onResponse: userName "+datum.getUserName());
                                int userId = datum.getUserId();
                                String userName = datum.getUserName();
                                PreferenceConnector.writeInteger(MainActivity.this, PreferenceConnector.USER_ID, userId);
                                PreferenceConnector.writeString(MainActivity.this, PreferenceConnector.USER_NAME, userName);
                                mProgressDialog.dismiss();
                                Intent intent = new Intent(MainActivity.this, Home.class);
                                intent.putExtra(INTENT_POSITION, 0);
                                startActivity(intent);
                                closeAllActivities();

                            } else {
                                mProgressDialog.dismiss();
                                new DialogUtil(MainActivity.this,response.body().getMessage()).show();
                            }
                        } else {
                            mProgressDialog.dismiss();
                            new DialogUtil(MainActivity.this,response.message()).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        mProgressDialog.dismiss();
                    }
                });
            } else {
                new DialogUtil(MainActivity.this,getResources().getString(R.string.no_internet_connection)).show();

            }
        }

    }
}
