package com.example.bootcompletedlistner;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

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

//        startServiceByAlarm(context);
    }

    /* Create an repeat Alarm that will invoke the background service for each execution time.
     * The interval time can be specified by your self.  */
    private void startServiceByAlarm(Context context) {
        // Get alarm manager.
        Logger.saveInternalStorage(context, "BootReceiver -> startServiceByAlarm");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Create intent to invoke the background service.
        Intent intent = new Intent(context, NotifyingDailyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long startTime = System.currentTimeMillis();
        long intervalTime = 60 * 1000;

        String message = "startServiceByAlarm Start service use repeat alarm. ";

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();


        // Create repeat alarm.
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                startTime,
                intervalTime,
                pendingIntent
        );
    }

}