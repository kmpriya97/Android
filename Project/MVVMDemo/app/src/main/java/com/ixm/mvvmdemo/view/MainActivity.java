package com.ixm.mvvmdemo.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ixm.mvvmdemo.R;
import com.ixm.mvvmdemo.app.AppBaseActivity;
import com.ixm.mvvmdemo.databinding.ActivityMainBinding;
import com.ixm.mvvmdemo.viewmodel.LoginModel;

public class MainActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        ActivityMainBinding activityMainBinding=DataBindingUtil.setContentView(this, R.layout.activity_main );
        activityMainBinding.setViewModelS( new LoginModel() );
        activityMainBinding.executePendingBindings();
        registerBaseActivityReceiver();
    }
    @BindingAdapter( {"toastMessage"} )
    public static void runMe(View view,String message){
        if(message!=null){
            Context context=view.getContext();
            Toast.makeText( view.getContext(), message, Toast.LENGTH_SHORT ).show();
            Intent intent=new Intent(context,SecondActivity.class);
            context.startActivity( intent );

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBaseActivityReceiver();
    }

}
