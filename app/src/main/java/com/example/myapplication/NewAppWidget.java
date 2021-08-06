package com.example.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String mSharedPrefFile =
            "com.example.myapplication";

    private static final String COUNT_KEY = "count";

    private static final String ACTIVITY1_STATE = "ACT1_STATE";

    public static String ACTION_SHOWMAIN="com.example.myapplication.NewAppWidget.MAIN_ACTION";

    /*@Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(ACTION_SHOWMAIN)) {
            Log.d("InentReceiver", "lets check this now");
            int[] idArray = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            if (idArray == null) {
                System.out.println("got idarray null");
            } else {
                System.out.println("got idarray size" + idArray.length);
            }

            Log.d("InentReceiver", intent.getAction());
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            remoteViews.setImageViewResource(R.id.imageButton, R.drawable.ic_action_name);

            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            for (int appWidgetId : idArray) {
                manager.updateAppWidget(appWidgetId, remoteViews);
            }
        }

        super.onReceive(context,intent);
    }*/

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        Log.d("NewAppWidget","starting the update");
        SharedPreferences prefs = context.getSharedPreferences(
                mSharedPrefFile, 0);
        SharedPreferences.Editor prefEditor = prefs.edit().clear();
        prefEditor.apply();

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        remoteViews.setOnClickPendingIntent(R.id.imageButton, buildButtonPendingIntent1(context,appWidgetIds));
        remoteViews.setOnClickPendingIntent(R.id.imageButton2, buildButtonPendingIntent2(context,appWidgetIds));
        remoteViews.setOnClickPendingIntent(R.id.button_reset, buildResetButtonPendingIntent(context,appWidgetIds));


        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);

    }

    private PendingIntent buildResetButtonPendingIntent(Context context, int[] appWidgetIds) {
        Intent intent = new Intent(context,CustomBroadcastReceiver.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        intent.setAction(CustomBroadcastReceiver.RESET_PRESSED);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /*static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        remoteViews.setOnClickPendingIntent(R.id.imageButton, buildButtonPendingIntent(context,appWidgetId));

        pushWidgetUpdate(context, remoteViews,appWidgetId);
    }*/

    public static PendingIntent buildButtonPendingIntent1(Context context,int[] appWidgetIds) {
        Intent intent = new Intent(context,CustomBroadcastReceiver.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        intent.setAction(CustomBroadcastReceiver.BUTTON1_PRESSED);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static PendingIntent buildButtonPendingIntent2(Context context,int[] appWidgetIds) {
        Intent intent = new Intent(context,CustomBroadcastReceiver.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        intent.setAction(CustomBroadcastReceiver.BUTTON2_PRESSED);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /*public static PendingIntent buildButtonPendingIntent(Context context,int appWidgetId) {
        Intent intent = new Intent();
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setAction(ACTION_SHOWMAIN);
        return PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }*/

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews,int appWidgetId) {
        //ComponentName myWidget = new ComponentName(context, NewAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(appWidgetId, remoteViews);
    }
    /*@Override
    public void onReceive(Context context, Intent intent) {
       Log.d("trynow","well lets try");
       if(intent.getAction().equals(ACTION_SHOWMAIN)){
          *//**//* AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
           Log.d("showmain","well lets try");
           int appWidgetId = intent.getIntExtra("appWidgetId",-1);
           RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
           views.setInt(R.id.imageButton, "setBackgroundResource", R.drawable.ic_action_name);
           appWidgetManager.updateAppWidget(appWidgetId, views);*//**//*
           //int appWidgetId = intent.getIntExtra("appWidgetId",-1);
          *//**//* RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
           views.setInt(R.id.imageButton, "setImageResource",R.drawable.ic_action_name);
           views.set*//**//*
          // appWidgetManager.updateAppWidget(appWidgetId, views);
           //AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
           int appWidgetId = intent.getIntExtra("sample",-1);
           Log.d("appwidgetId","appwid id is "+appWidgetId);
           //updateAppWidget(context,appWidgetManager,appWidgetId);
       }
       super.onReceive(context,intent);
    }
*//*
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Log.d("appwidgetId","appwid id is "+appWidgetId);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        Intent active = new Intent(context, NewAppWidget.class);
        active.putExtra("sample",111);
        active.setAction(ACTION_SHOWMAIN);
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 234, active, 0);

       *//* SharedPreferences prefs = context.getSharedPreferences(
                mSharedPrefFile, 0);
        if(prefs.getInt(ACTIVITY1_STATE , 0) == 0){
            views.setImageViewResource(R.id.imageButton, R.drawable.ic_action_name);
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putInt(ACTIVITY1_STATE, 1);
            prefEditor.apply();
        }else{
            views.setImageViewResource(R.id.imageButton, R.drawable.ic_stop);
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putInt(ACTIVITY1_STATE, 0);
            prefEditor.apply();
        }*//*



        //views.setInt(R.id.imageButton, "setImageResource",R.drawable.ic_stop);
        views.setOnClickPendingIntent(R.id.imageButton, actionPendingIntent);


        appWidgetManager.updateAppWidget(appWidgetId, views);
       *//* SharedPreferences prefs = context.getSharedPreferences(
                mSharedPrefFile, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        Intent active = new Intent(context, NewAppWidget.class);
        active.setAction(ACTION_SHOWMAIN);
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 234, active, 0);
        views.setOnClickPendingIntent(R.id.imageButton, actionPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);*//*

        *//*SharedPreferences prefs = context.getSharedPreferences(
                mSharedPrefFile, 0);
        int count = prefs.getInt(COUNT_KEY + appWidgetId, 0);
        count++;

        String dateString =
                DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());



        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        *//**//*views.setTextViewText(R.id.appwidget_id, String.valueOf(appWidgetId));
        views.setTextViewText(R.id.appwidget_update,
                context.getResources().getString(
                        R.string.date_count_format, count, dateString));*//**//*
        Log.d("lolwan","hello world");

        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putInt(COUNT_KEY + appWidgetId, count);
        prefEditor.apply();

        Intent intentUpdate = new Intent(context, NewAppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] idArray = new int[]{appWidgetId};

        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,idArray);
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(
                context, appWidgetId, intentUpdate,
                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.button_update,pendingUpdate);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);*//*
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }*/

}