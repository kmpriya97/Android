package com.ixm.demoexperimenttwo;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListAdapter.ItemListener {
    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;
    Context context = this;
    MediaPlayer mp;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView = findViewById( R.id.recyclerView );
        mp = MediaPlayer.create(context, R.raw.sound);
        imageButton=findViewById( R.id.imageButton );
        arrayList = new ArrayList<>();
        arrayList.add( new DataModel( "Item 1", "#09A9FF" ) );
        arrayList.add( new DataModel( "Item 2", "#3E51B1" ) );
        arrayList.add( new DataModel( "Item 3", "#673BB7" ) );
        arrayList.add( new DataModel( "Item 4", "#4BAA50" ) );
        arrayList.add( new DataModel( "Item 5", "#F94336" ) );
        arrayList.add( new DataModel( "Item 6", "#0A9B88" ) );

        ListAdapter adapter = new ListAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager( this, 250 );
        recyclerView.setLayoutManager( layoutManager );
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Clicked ME####", Toast.LENGTH_SHORT ).show();
            }
        } );
    }


    @Override
    public void onItemClick(DataModel item) {
        try {
            Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(context, R.raw.sound);
            } mp.start();
        } catch(Exception e) { e.printStackTrace(); }
    }

}

