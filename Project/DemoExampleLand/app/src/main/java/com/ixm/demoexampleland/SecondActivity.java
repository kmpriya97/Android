package com.ixm.demoexampleland;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
ImageButton ivBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
        ivBtn=findViewById( R.id.ivBtn );
        ivBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( SecondActivity.this, "Floating Btn Clicked!!!", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
