package com.example.myapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppWidgetProvider{
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
        Log.d("mainAct","hello running update");
        int number = (new Random().nextInt(100));
        for (int appWidgetId : appWidgetIds) {


            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.activity_main);
            views.setTextViewText(R.id.text1, String.valueOf(number));
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

    @Override
    public void onEnabled(Context context) {
        //Log.u(Log.INFO,"activity","let try log hisdfs");
        Log.i("aciti","let try this");
        super.onEnabled(context);
    }
}