package com.example.projectmv3.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.projectmv3.views.MainActivity;

//service to check if internet is available
public class InternetAvailabilityService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("inside", "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Note yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(periodicUpdate);
        return START_STICKY;
    }

    public boolean isOnline (Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni != null && ni.isConnectedOrConnecting()){
            return true;
        }else
        {
            return  false;
        }
    }

    Handler handler = new Handler();
    private Runnable periodicUpdate = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(periodicUpdate, 1000 - SystemClock.elapsedRealtime()%1000);
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(MainActivity.BROADCAST_INTERNET_STRING);
            broadcastIntent.putExtra("online_status", ""+isOnline(InternetAvailabilityService.this));
            sendBroadcast(broadcastIntent);
        }
    };
}