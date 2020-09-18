package com.example.bootcompletedlistner;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final String TAG = "BootCompletedReceiver";

    @Override
    public void onReceive(Context context, Intent arg1) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onReceive: starting service...");
        Logger.saveInternalStorage(context,"onReceive: starting service...");


        Intent intentReset = new Intent(context, NotifyingDailyService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ContextCompat.startForegroundService(context, intentReset);
        } else {
            context.startService(intentReset);
        }
    }

}