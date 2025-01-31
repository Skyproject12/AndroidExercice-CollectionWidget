package com.example.imagewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

public class ImageWidget extends AppWidgetProvider {

    private static final String TOAST_ACTION="com.dicoding.picodiploma.TOAST_ACTION";
    public static final String EXTRA_ITEM= "com.dicoding.picodiploma.EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        // intent to service to send data from widget
        Intent intent= new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        // set layout imagewidget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.image_widget);
        // set adapter use intent with stack_view
        views.setRemoteAdapter(R.id.stack_view, intent);
        // set empty data
        views.setEmptyView(R.id.stack_view, R.id.empty_view);

        Intent toastIntent= new Intent(context, ImageWidget.class);
        // set action intent
        toastIntent.setAction(ImageWidget.TOAST_ACTION);
        // stack_view to take image like list view
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        // merespon broadcash ketika melakukan pending
        PendingIntent pendingIntent= PendingIntent.getBroadcast(context,0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // set listview in widget to take pending
        views.setPendingIntentTemplate(R.id.stack_view,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    // receive broadcash
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        // check action null or not
        if(intent.getAction()!=null){
            // check action
            if(intent.getAction().equals(TOAST_ACTION)){
                // intent get data from broadcash
                int viewIndex= intent.getIntExtra(EXTRA_ITEM,0);
                Toast.makeText(context, "Touched view"+viewIndex, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

