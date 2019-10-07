package com.example.imagewidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import java.util.Stack;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        // definition StackRemoteViewFactory in service
        return new StackRemoteViewFactory(this.getApplicationContext());
    }
}
