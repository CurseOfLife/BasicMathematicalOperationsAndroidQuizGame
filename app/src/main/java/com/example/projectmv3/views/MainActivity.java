package com.example.projectmv3.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.projectmv3.R;
import com.example.projectmv3.services.InternetAvailabilityService;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_INTERNET_STRING = "checkInternet";

    private IntentFilter mIntentFilter;

    private RelativeLayout no_internet_mask;
    private AppCompatButton btn_test_connectivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test_connectivity = findViewById(R.id.btn_test_connectivity);
        no_internet_mask = findViewById(R.id.no_internet_mask);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BROADCAST_INTERNET_STRING);

        Intent serviceIntent = new Intent(this, InternetAvailabilityService.class);
        startService(serviceIntent);

        //checking if user has internet
        if (isOnline(getApplicationContext())) {
            set_visibility_ON();
        } else {
            set_visibility_OFF();
        }

        btn_test_connectivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnline(getApplicationContext())) {
                    set_visibility_ON();
                } else {
                    Toast.makeText(MainActivity.this, "No internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //we have to watch in the background if the user lost internet
    public BroadcastReceiver internetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(BROADCAST_INTERNET_STRING)) {
                if (intent.getStringExtra("online_status").equals("true")) {
                    set_visibility_ON();
                } else {
                    set_visibility_OFF();
                }
            }
        }
    };

    public void set_visibility_ON() {
        no_internet_mask.setVisibility(View.GONE);
    }

    public void set_visibility_OFF() {
        no_internet_mask.setVisibility(View.VISIBLE);
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(internetReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(internetReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(internetReceiver, mIntentFilter);
    }
}