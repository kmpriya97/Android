package com.ixm.demoexperimentone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign extends AppCompatActivity {
   // EditText etUserName;
   // EditText etPassword;
    TextInputEditText etUserName,etPassword;
    //TextInputLayout layout1,layout2;
    Button btSignin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signin );
        etUserName = findViewById( R.id.et_user );
        etPassword = findViewById( R.id.et_pass );
        btSignin = findViewById( R.id.bt_signin );
        //layout1=findViewById( R.id.textInputLayout );
        // layout2=findViewById( R.id.textInputLayout2 );


        btSignin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = etUserName.getText().toString();
                Log.i( "userName", "onCreate: " + userName );
                final String passWord = etPassword.getText().toString();
                Log.i( "passWord", "onCreate: " + passWord );
                /*      if ((etUserName.getText().toString() == "priya") && (etPassword.getText().toString() == "123456"))*/
                if ((userName.equals( "priya" ))&&(passWord.equals( "1234" ))){
                    Intent intent = new Intent( Sign.this, ContactInformation.class );
                    intent.putExtra( "name", "Priya" );
                    intent.putExtra( "PhoneNumber", "9750599787" );
                    startActivity( intent );
                } else {
                    Toast.makeText( Sign.this, "Invalid User Name & Password !!!", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
}
