package com.gaparmar.traq;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.twilio.client.Connection;
import com.twilio.client.Device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

//Gavs imports
//Gavs imports

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter BA;

    private static final String TOKEN_SERVICE_URL = "http://default-environment.bmicg4bdyt.us-east-2.elasticbeanstalk.com/token";
    private static final int MIC_PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = MainActivity.class.getName();
    private Device clientDevice;
    private Connection activeConnection;
    private Connection pendingConnection;
    private AlertDialog alertDialog;
    private AudioManager audioManager;
    private int savedAudioMode = AudioManager.MODE_INVALID;
    public static final String ACCOUNT_SID = "AC673fa27e32d2aea308f77bd9b020aede";
    public static final String AUTH_TOKEN = "5a340b4559a11cf9d02be8bf693d5d14";
    private ArrayList<String> devices;

    Button titleButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    private LinkedList <String> names;
    private LinkedList <Button> btnList;
    private LinkedList <String> locList;
    private LinkedList <String> timeList;
    private LinkedList <Boolean> connectedList;

    //Gav
    protected class ClientProfile {
        private String name;
        private boolean allowOutgoing = true;
        private boolean allowIncoming = true;


        public ClientProfile(String name, boolean allowOutgoing, boolean allowIncoming) {
            this.name = name;
            this.allowOutgoing = allowOutgoing;
            this.allowIncoming = allowIncoming;
        }

        public String getName() {
            return name;
        }

        public boolean isAllowOutgoing() {
            return allowOutgoing;
        }

        public boolean isAllowIncoming() {
            return allowIncoming;
        }
    }
    private ClientProfile clientProfile;
    //Gav

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn,0);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);

        titleButton = (Button)findViewById(R.id.titleButton);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        btnList = new LinkedList<>();
        btnList.add(button1);
        btnList.add(button2);
        btnList.add(button3);
        btnList.add(button4);


        names = DataHolder.getInstance().getNames();
        connectedList = DataHolder.getInstance().getConnectedList();
        updateUI();
        locList = DataHolder.getInstance().getlocList();
        timeList = DataHolder.getInstance().getTimeList();


        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this, Pop.class));
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (MainActivity.this, ActivityInfo.class);

                i.putExtra("name", names.get(0));
                i.putExtra("loc", locList.get(0));
                i.putExtra("time", timeList.get(0));
                i.putExtra("color", "#F4BB72");

                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, ActivityInfo.class);

                i.putExtra("name", names.get(1));
                i.putExtra("loc", locList.get(1));
                i.putExtra("time", timeList.get(1));
                i.putExtra("color", "#FF83A7");

                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, ActivityInfo.class);

                i.putExtra("name", names.get(2));
                i.putExtra("loc", locList.get(2));
                i.putExtra("time", timeList.get(2));
                i.putExtra("color", "#7CECEE");

                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, ActivityInfo.class);

                i.putExtra("name", names.get(3));
                i.putExtra("loc", locList.get(3));
                i.putExtra("time", timeList.get(3));
                i.putExtra("color", "#B0D875");

                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }//The BroadcastReceiver that listens for bluetooth broadcasts
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String name = device.getName();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //newBluetooth(device.getName());
            }
            else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                newBluetooth(device.getName());
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {

            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                lostConnection(name);
            }
        }
    };



    private void updateUI(){
        names = DataHolder.getInstance().getNames();
        for( int i=0; i<names.size(); i++ ){
            Button but = btnList.get(i);
            but.setText(names.get(i));
            if(connectedList.get(i)) {
                but.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.bluetooth, 0, R.drawable.ic_find_next_mtrl_alpha, 0);
            } else {
                but.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_print_error, 0, R.drawable.ic_find_next_mtrl_alpha, 0);
            }
        }
    }

    private void updateVars(){
        names = DataHolder.getInstance().getNames();
        connectedList = DataHolder.getInstance().getConnectedList();
        locList = DataHolder.getInstance().getlocList();
        timeList = DataHolder.getInstance().getTimeList();

    }


    private void newBluetooth(String newName) {

        //new time
        String time = "" + Calendar.getInstance().getTime();

        //new location
        double lat = 0.0;
        double lon = 0.0;
        GpsTracker gt = new GpsTracker(getApplicationContext());
        Location l = gt.getLocation();
        if( l == null){
            //Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            lat = l.getLatitude();
            lon = l.getLongitude();
            //Toast.makeText(getApplicationContext(),"GPS Lat = "+lat+"\n lon = "+lon,Toast.LENGTH_SHORT).show();
        }

        DataHolder.getInstance().addNew(newName, "" + lat + ", " + lon, time);


        updateVars();
        updateUI();

    }


    private void lostConnection( String name ) {
        names = DataHolder.getInstance().getNames();
        int indexOf = names.indexOf(name);

        //retrive time
        String time = "" + Calendar.getInstance().getTime();

        //retrive location
        double lat = 0.0;
        double lon = 0.0;
        GpsTracker gt = new GpsTracker(getApplicationContext());
        Location l = gt.getLocation();
        if( l == null){
            //Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            lat = l.getLatitude();
            lon = l.getLongitude();
        }

        String location =  "" + lat + ", " + lon;

        DataHolder.getInstance().disconnected(indexOf, time, location);
        updateVars();
        updateUI();

        //new SmsSender(DataHolder.getInstance().getPhoneNum(), "Lost Connection with " + name + " at: " + location);
        new SmsSender(DataHolder.getInstance().getPhoneNum(), name , location, time );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
