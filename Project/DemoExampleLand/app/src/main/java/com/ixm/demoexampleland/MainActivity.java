package com.ixm.demoexampleland;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener {
    TextView tvDate;
    Spinner spType;
    String RESULTS = "results";
    String ID = "id";
    String NAME = "name";
    String COUNT = "count";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
int position;
    ImageButton ivBtn;

    ListView listView, listView1;

    String[] Type = new String[]{"Type 1", "Type 2", "Type 3"};

    ArrayList<DataModel> arrayList;
    Context context = this;
    ModelNew modelNew;
    AdapterNew configurationAdapter;
    List<ModelNew> configModelList = new ArrayList<>();
    static final int DATE_DIALOG_ID = 0;
    private int mYear, mMonth, mDay;
    MediaPlayer mp;
    int itemCount = 1;
    int itemCount1 = 1;
    int itemCount2 = 1;
    int itemCount3 = 1;
    int itemCount4 = 1;
    int itemCount5 = 1;
    String Items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getSupportActionBar().hide();
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );

        configModelList = new ArrayList<>();

        spType = findViewById( R.id.spinner1 );
        tvDate = findViewById( R.id.tvDate );
        ivBtn = findViewById( R.id.iv_btn );
        mp = MediaPlayer.create( context, R.raw.sound );
        setValues();
        //  JsonCreate();
        try {
            getListItems();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_layout, Type );
        spinnerArrayAdapter.setDropDownViewResource( R.layout.spinner_layout );
        spType.setAdapter( spinnerArrayAdapter );
        ivBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                Intent intent = new Intent( MainActivity.this, SecondActivity.class );
                startActivity( intent );
            }
        } );
        Calendar c = Calendar.getInstance();
        mYear = c.get( Calendar.YEAR );
        mMonth = c.get( Calendar.MONTH );
        mDay = c.get( Calendar.DAY_OF_MONTH );
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
        tvDate.setText( sdf.format( c.getTime() ) );


        tvDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( MainActivity.this, date, myCalendar
                        .get( Calendar.YEAR ), myCalendar.get( Calendar.MONTH ),
                        myCalendar.get( Calendar.DAY_OF_MONTH ) ).show();
            }

        } );

    }

    /*   void JsonCreate() {
           JSONObject data1 = new JSONObject();
           try {
               data1.put( "id", "1" );
               data1.put( "name", "Item 1" );
               data1.put( "count", "0" );

           } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }

           JSONObject data2 = new JSONObject();
           try {
               data2.put( "id", "2" );
               data2.put( "name", "Item 2" );
               data2.put( "count", "0" );

           } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           JSONObject data3 = new JSONObject();
           try {
               data3.put( "id", "3" );
               data3.put( "name", "Item 3" );
               data3.put( "count", "0" );

           } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           JSONObject data4 = new JSONObject();
           try {
               data4.put( "id", "4" );
               data4.put( "name", "Item 4" );
               data4.put( "count", "0" );

           } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           JSONObject data5 = new JSONObject();
           try {
               data5.put( "id", "5" );
               data5.put( "name", "Item 5" );
               data5.put( "count", "0" );

           } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           JSONObject data6 = new JSONObject();
           try {
               data6.put( "id", "6" );
               data6.put( "name", "Item 6" );
               data6.put( "count", "0" );

           } catch (JSONException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }


           JSONArray jsonArray = new JSONArray();

           jsonArray.put( data1 );
           jsonArray.put( data2 );
           jsonArray.put( data3 );
           jsonArray.put( data4 );
           jsonArray.put( data5 );
           jsonArray.put( data6 );
           JSONObject studentsObj = new JSONObject();
           try {
               studentsObj.put( "Items", jsonArray );
           } catch (JSONException e) {
               e.printStackTrace();
           }

           String jsonStr = studentsObj.toString();
           System.out.println( "jsonString: " + jsonStr );
           Log.i( "jsonString", "JsonCreate: " + jsonStr );
       }
   */
    void getListItems() throws JSONException {

        String arrRes = "[{\"id\" : \"1\", \"name\" : \"item 1\", \"count\" : \"0\"}, {\"id\" : \"2\", \"name\" : \"item 2\", \"count\" : \"0\"}, {\"id\" : \"3\", \"name\" : \"item 3\", \"count\" : \"0\"}, {\"id\" : \"4\", \"name\" : \"item 4\", \"count\" : \"0\"}, {\"id\" : \"5\", \"name\" : \"item 5\", \"count\" : \"0\"}, {\"id\" : \"6\", \"name\" : \"item 6\", \"count\" : \"0\"}]";
        JSONArray jsonArrayResults = null;
        try {
            jsonArrayResults = new JSONArray( arrRes );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int index = 0; index < jsonArrayResults.length(); index++) {
            JSONObject jsonObject1 = null;
            try {
                jsonObject1 = jsonArrayResults.getJSONObject( index );
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int id = jsonObject1.getInt( ID );
            String name = jsonObject1.getString( NAME );
            String count = jsonObject1.getString( COUNT );
            Log.i( "##$", "getListItems: " + id );
            Log.i( "##$", "getListItems: " + name );
            Log.i( "##$", "getListItems: " + count );
            configModelList.add( new ModelNew( id, name, count ) );
        }
        if (configModelList.size() > 0) {
            configurationAdapter = new AdapterNew( configModelList );
            Log.i( "configModelList.size", "getListItems: " + configModelList.size() );
            configurationAdapter = new AdapterNew( configModelList );
            recyclerView.setHasFixedSize( true );
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() );
            linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
            recyclerView.setLayoutManager( linearLayoutManager );
            recyclerView.addItemDecoration( new SimpleDividerItemDecoration( this ) );
            recyclerView.setItemAnimator( new DefaultItemAnimator() );
            recyclerView.setAdapter( configurationAdapter );
            configurationAdapter.notifyDataSetChanged();
        }
    }


    void setValues() {
        RecyclerView recyclerView = findViewById( R.id.recycler );
        arrayList = new ArrayList<>();
        arrayList.add( new DataModel( "Item 1", R.drawable.sample_0, "#09A9FF" ) );
        arrayList.add( new DataModel( "Item 2", R.drawable.sample_1, "#3E51B1" ) );
        arrayList.add( new DataModel( "Item 3", R.drawable.sample_2, "#673BB7" ) );
        arrayList.add( new DataModel( "Item 4", R.drawable.sample_3, "#4BAA50" ) );
        arrayList.add( new DataModel( "Item 5", R.drawable.sample_4, "#F94336" ) );
        arrayList.add( new DataModel( "Item 6", R.drawable.sample_5, "#0A9B88" ) );
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( this, arrayList, this );
        recyclerView.setAdapter( adapter );
        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager( this, 500 );
        recyclerView.setLayoutManager( layoutManager );
    }

    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set( Calendar.YEAR, year );
            myCalendar.set( Calendar.MONTH, monthOfYear );
            myCalendar.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat( myFormat, Locale.US );
        tvDate.setText( sdf.format( myCalendar.getTime() ) );
    }

    @Override
    public void onItemClick(DataModel item) {
        Toast.makeText( getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT ).show();
        try {
            if ((item.text == "Item 1") && (itemCount >= 0)) {
                position=0;
                String count = String.valueOf( itemCount );
                Toast.makeText( context, item.text + " Count is " + count, Toast.LENGTH_SHORT ).show();
                Log.i( "##", "onItemClick: " + item.text );
                Log.i( "##", "onItemClick: " + count );
                configModelList.get( position ).setItemCount( count );
                configurationAdapter.notifyDataSetChanged();
                itemCount++;

            } else if ((item.text == "Item 2") && (itemCount1 >= 0)) {
                position=1;
                String count = String.valueOf( itemCount1 );
                Toast.makeText( context, item.text + " Count is " + count, Toast.LENGTH_SHORT ).show();
                Log.i( "##", "onItemClick: " + item.text );
                Log.i( "##", "onItemClick: " + count );
                configModelList.get( position ).setItemCount( count );
                configurationAdapter.notifyDataSetChanged();
               // configModelList.add( new ModelNew( 2, item.text, count ) );

                itemCount1++;
            } else if ((item.text == "Item 3") && (itemCount2 >= 0)) {
                position=2;
                String count = String.valueOf( itemCount2 );
                Toast.makeText( context, item.text + " Count is " + count, Toast.LENGTH_SHORT ).show();
                Log.i( "##", "onItemClick: " + item.text );
                Log.i( "##", "onItemClick: " + count );
                //  configModelList.add( new ModelNew( 3, item.text, count ) );
                configModelList.get( position ).setItemCount( count );
                configurationAdapter.notifyDataSetChanged();
                itemCount2++;
            } else if ((item.text == "Item 4") && (itemCount3 >= 0)) {
                position=3;
                String count = String.valueOf( itemCount3 );
                Toast.makeText( context, item.text + " Count is " + count, Toast.LENGTH_SHORT ).show();
                Log.i( "##", "onItemClick: " + item.text );
                Log.i( "##", "onItemClick: " + count );
                configModelList.get( position ).setItemCount( count );
                configurationAdapter.notifyDataSetChanged();
                //  configModelList.add( new ModelNew( 4, item.text, count ) );
                itemCount3++;
            } else if ((item.text == "Item 5") && (itemCount4 >= 0)) {
                position=4;
                String count = String.valueOf( itemCount4 );
                Toast.makeText( context, item.text + " Count is " + count, Toast.LENGTH_SHORT ).show();
                Log.i( "##", "onItemClick: " + item.text );
                Log.i( "##", "onItemClick: " + count );
                configModelList.get( position ).setItemCount( count );
                configurationAdapter.notifyDataSetChanged();
                // configModelList.add( new ModelNew( 5, item.text, count ) );
                itemCount4++;
            } else if ((item.text == "Item 6") && (itemCount5 >= 0)) {
                position=5;
                String count = String.valueOf( itemCount5 );
                Toast.makeText( context, item.text + " Count is " + count, Toast.LENGTH_SHORT ).show();
                Log.i( "##", "onItemClick: " + item.text );
                Log.i( "##", "onItemClick: " + count );
                configModelList.get( position ).setItemCount( count );
                configurationAdapter.notifyDataSetChanged();
                //   configModelList.add( new ModelNew( 6, item.text, count ) );
                itemCount5++;
            }
            configurationAdapter = new AdapterNew( configModelList );
            recyclerView.setHasFixedSize( true );
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() );
            linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
            recyclerView.setLayoutManager( linearLayoutManager );
            recyclerView.addItemDecoration( new SimpleDividerItemDecoration( this ) );
            recyclerView.setItemAnimator( new DefaultItemAnimator() );
            recyclerView.setAdapter( configurationAdapter );
            configurationAdapter.notifyDataSetChanged();
            new ModelNew().getInstance().setData( (ArrayList<ModelNew>) configModelList );
            Log.i( "MainActivity###", "getConfigurationDetails: " + configModelList.size() );

            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create( context, R.raw.sound );
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
