package com.example.bootcompletedlistner;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public abstract class Logger {

    private static final String TAG = "Logger";
    private static final Date date = new Date();



    public static void saveInternalStorage(Context context, String logMessage) {

        File sdcard = context.getFilesDir();

        File folder = new File(sdcard.getAbsoluteFile(), "/mylogs/");
        if(!folder.exists()){
            folder.mkdir();
        }
        File file = new File(folder.getAbsoluteFile(),"finally.txt");
        Log.d(TAG, "saveInternalStorage: "+ file.getAbsolutePath());

        String logMessageWithTimestamp = date.toLocaleString() + " - " + logMessage + "\n";

        try {
            FileOutputStream out = new FileOutputStream(file, true);
            out.write(logMessageWithTimestamp.getBytes());
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
