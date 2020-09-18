package com.example.bootcompletedlistner;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public abstract class Logger {

    private static final String TAG = "Logger";
    private static FileWriter fw;
    private static final Date date = new Date();
    private final static String APP = "logfileGoplus";

    public static void log(Throwable ex) {
        log(ex.getMessage());
        for (StackTraceElement ste : ex.getStackTrace()) {
            log(ste.toString());
        }
    }

    public static void log(final Cursor c) {
        if (!BuildConfig.DEBUG) return;
        c.moveToFirst();
        String title = "";
        for (int i = 0; i < c.getColumnCount(); i++)
            title += c.getColumnName(i) + "\t| ";
        log(title);
        while (!c.isAfterLast()) {
            title = "";
            for (int i = 0; i < c.getColumnCount(); i++)
                title += c.getString(i) + "\t| ";
            log(title);
            c.moveToNext();
        }
    }

    @SuppressWarnings("deprecation")
    public static void log(String msg) {
        if (!BuildConfig.DEBUG) return;
          try {
            if (fw == null) {
                fw = new FileWriter(new File(
                        Environment.getExternalStorageDirectory().toString() + "/" + APP + ".txt"),
                        true);
            }
            date.setTime(System.currentTimeMillis());
            fw.write(date.toLocaleString() + " - " + msg + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (fw != null) fw.close();
        } finally {
            super.finalize();
        }
    }


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
