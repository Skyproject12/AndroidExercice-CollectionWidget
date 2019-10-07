package com.example.imagewidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.os.Bundle;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class StackRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private final List<Bitmap> mWidgetItems= new ArrayList<>();
    private final Context mContext;
    StackRemoteViewFactory(Context context){
        mContext= context;
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        // save all image to list
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.poster_aquaman));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.poster_birdbox));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.poster_creed));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.poster_deadpool));
        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.poster_bumblebee));
    }

    @Override
    public void onDestroy() {

    }


    // save size the list
    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        // save image to RemoteView use bitmap with position
        RemoteViews remoteViews= new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        remoteViews.setImageViewBitmap(R.id.imageView, mWidgetItems.get(i));
        Bundle extras= new Bundle();
        extras.putInt(ImageWidget.EXTRA_ITEM, i);
        Intent intent= new Intent();
        intent.putExtras(extras);
        // setclick bitmap with putExtra
        remoteViews.setOnClickFillInIntent(R.id.imageView, intent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
