package com.ixm.demoexperimentone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactInformation extends AppCompatActivity {
    TextView Name;
    TextView PhoneNo;
    Button btWay;
    ImageView back,logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contact_information );
        Name = findViewById( R.id.tv_name );
        PhoneNo = findViewById( R.id.tv_phno );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        final Intent intent = getIntent();
        String name = intent.getStringExtra( "name" );
        String phNo = intent.getStringExtra( "PhoneNumber" );
        Name.setText( name );
        PhoneNo.setText( phNo );
        btWay=findViewById( R.id.waytohome );
        btWay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ContactInformation.this,Home.class);
                startActivity( intent1 );
            }
        } );
        back=findViewById( R.id.ivBack );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        /*logout=findViewById( R.id.logout );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactInformation.this,Sign.class);
                startActivity( intent );
            }
        } );*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Intent intent=new Intent(ContactInformation.this,Sign.class);
                startActivity( intent );
                Toast.makeText( getApplicationContext(), "logout Successfully!!", Toast.LENGTH_LONG ).show();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }
}

