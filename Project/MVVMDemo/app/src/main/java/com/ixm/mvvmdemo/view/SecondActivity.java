package com.ixm.mvvmdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ixm.mvvmdemo.R;
import com.ixm.mvvmdemo.app.AppBaseActivity;

public class SecondActivity extends AppBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new );
        registerBaseActivityReceiver();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBaseActivityReceiver();
    }
}
