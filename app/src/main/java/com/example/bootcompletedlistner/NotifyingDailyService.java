package com.example.bootcompletedlistner;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static com.example.bootcompletedlistner.App.CHANNEL_ID;

public class NotifyingDailyService extends Service {
    private static final String TAG = "NotifyingDailyService";

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub

        // START FOREGROUND CAN ALSO BE CALLED  HERE
        return null;
    }

    @Override
    public int onStartCommand(Intent pIntent, int flags, int startId) {
        // TODO Auto-generated method stub
        Logger.log("onStartCommand NotifyingDailyService");
        Toast.makeText(this, "NotifyingDailyService", Toast.LENGTH_LONG).show();

        Log.d(TAG, "onStartCommand: NotifyingDailyService");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent  pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Content title")
                .setContentText("Context text")
                .setSmallIcon(R.drawable.ic_baseline_access_time_24)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

//        return super.onStartCommand(pIntent, flags, startId);

        return START_STICKY;
    }
}