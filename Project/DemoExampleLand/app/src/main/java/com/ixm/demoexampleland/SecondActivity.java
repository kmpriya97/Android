package com.ixm.demoexampleland;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static com.ixm.demoexampleland.MainActivity.REQUEST_CODE;

public class SecondActivity extends AppCompatActivity{
ImageButton ivBtn;
Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
        ivBtn=findViewById( R.id.ivBtn );
        ivBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( SecondActivity.this, "Floating Btn Clicked!!!", Toast.LENGTH_SHORT ).show();
                Intent intent = getIntent();
                intent.putExtra("key", "1");
                setResult(RESULT_OK, intent);
                finish();

            }
        } );

    }


}
