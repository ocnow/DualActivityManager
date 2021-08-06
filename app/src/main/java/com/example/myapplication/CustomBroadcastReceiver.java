package com.example.myapplication;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    public static final String BUTTON1_PRESSED = "com.example.myapplication.CustomBroadcastReceiver.BUTTON1_PRESSED";
    public static final String BUTTON2_PRESSED = "com.example.myapplication.CustomBroadcastReceiver.BUTTON2_PRESSED";
    public static final String RESET_PRESSED = "com.example.myapplication.CustomBroadcastReceiver.RESET_PRESSED";

    public static final String ACTIVITY1_TOTAL_TIME = "ACTIVITY1_TOTAL_TIME";
    public static final String ACTIVITY2_TOTAL_TIME = "ACTIVITY2_TOTAL_TIME";

    public static final String ACTIVITY1_START_TIME = "ACTIVITY1_START_TIME";
    public static final String ACTIVITY2_START_TIME = "ACTIVITY2_START_TIME";

    private static final String mSharedPrefFile =
            "com.example.myapplication";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("CustomBroadcastReceiver",intent.getAction());
        int[] idArray = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        if(intent.getAction().equals(BUTTON1_PRESSED) || intent.getAction().equals(BUTTON2_PRESSED)){
            SharedPreferences prefs = context.getSharedPreferences(
                    mSharedPrefFile, 0);
            SharedPreferences.Editor prefEditor = prefs.edit();

            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date current = Calendar.getInstance().getTime();

            if(intent.getAction().equals(BUTTON1_PRESSED)){
                int count = prefs.getInt("ACTIVITY_1_STATE", 0);

                if(count == 0){
                    remoteViews.setImageViewResource(R.id.imageButton, R.drawable.ic_stop);

                    prefEditor.putString(ACTIVITY1_START_TIME,df.format(current));
                    prefEditor.putInt("ACTIVITY_1_STATE", 1);
                }else{
                    long prevTotalTime = prefs.getLong(ACTIVITY1_TOTAL_TIME,0);
                    Date prevStartTime = current;
                    try {
                        prevStartTime = df.parse(prefs.getString(ACTIVITY1_START_TIME, "00/00/0000 00:00:00"));
                    }catch(java.text.ParseException e){
                        System.out.println("got the parseException");
                    }
                    long activityRanFor = (current.getTime() - prevStartTime.getTime())/1000;
                    prevTotalTime = prevTotalTime + activityRanFor;
                    prefEditor.putLong(ACTIVITY1_TOTAL_TIME,prevTotalTime);

                    remoteViews.setTextViewText(R.id.act1timeLabel,secondsToHours((prevTotalTime)));
                    remoteViews.setImageViewResource(R.id.imageButton, R.drawable.ic_action_name);
                    prefEditor.putInt("ACTIVITY_1_STATE", 0);
                }
            }else{
                int count = prefs.getInt("ACTIVITY_2_STATE", 0);

                if(count == 0){
                    remoteViews.setImageViewResource(R.id.imageButton2, R.drawable.ic_stop);

                    prefEditor.putString(ACTIVITY2_START_TIME,df.format(current));
                    prefEditor.putInt("ACTIVITY_2_STATE", 1);
                }else{
                    long prevTotalTime = prefs.getLong(ACTIVITY2_TOTAL_TIME,0);
                    Date prevStartTime = current;
                    try {
                        prevStartTime = df.parse(prefs.getString(ACTIVITY2_START_TIME, "00/00/0000 00:00:00"));
                    }catch(java.text.ParseException e){
                        System.out.println("got the parseException");
                    }
                    long activityRanFor = (current.getTime() - prevStartTime.getTime())/1000;
                    prevTotalTime = prevTotalTime + activityRanFor;
                    prefEditor.putLong(ACTIVITY2_TOTAL_TIME,prevTotalTime);

                    remoteViews.setTextViewText(R.id.act2timeLabel,secondsToHours((prevTotalTime)));

                    remoteViews.setImageViewResource(R.id.imageButton2, R.drawable.ic_action_name);
                    prefEditor.putInt("ACTIVITY_2_STATE", 0);
                }
            }
            prefEditor.apply();
        }else if(intent.getAction().equals(RESET_PRESSED)){
            Log.d("RESET","RESET PRESSED");
            remoteViews.setImageViewResource(R.id.imageButton, R.drawable.ic_action_name);
            remoteViews.setImageViewResource(R.id.imageButton2, R.drawable.ic_action_name);
            remoteViews.setTextViewText(R.id.act1timeLabel,"00:00:00");
            remoteViews.setTextViewText(R.id.act2timeLabel,"00:00:00");
            SharedPreferences prefs = context.getSharedPreferences(
                    mSharedPrefFile, 0);
            SharedPreferences.Editor prefEditor = prefs.edit().clear();
            prefEditor.apply();
        }

        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        for (int appWidgetId : idArray) {
            manager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private static String secondsToHours(long seconds){
        int p1 = (int)seconds % 60;
        int p2 = (int)seconds / 60;
        int p3 = (int)p2 % 60;
        p2 = p2 / 60;
        return String.format("%02d",p2) + ":" + String.format("%02d",p3) + ":" + String.format("%02d",p1);
    }

}
