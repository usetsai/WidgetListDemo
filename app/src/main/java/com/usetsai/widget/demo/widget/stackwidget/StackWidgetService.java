package com.usetsai.widget.demo.widget.stackwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.usetsai.widget.demo.R;

import java.util.ArrayList;
import java.util.List;

public class StackWidgetService extends RemoteViewsService {
    private final static String TAG = "StackWidgetService";
    public static final String TOAST_ACTION = "com.usetsai.widget.demo.widget.stackwidget.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.usetsai.widget.demo.widget.stackwidget.EXTRA_ITEM";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.i(TAG, "StackWidgetService onGetViewFactory !");
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class WidgetListItem {
    public String text;

    public WidgetListItem(String text) {
        this.text = text;
    }
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final static String TAG = "StackRemoteViewsFactory";
    private final List<WidgetListItem> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    private int mAppWidgetId;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        Log.i(TAG, "StackWidgetService StackRemoteViewsFactory mAppWidgetId : " + mAppWidgetId);
    }

    public void onCreate() {
        for (int i = 0; i < 10; i++) {
            mWidgetItems.add(new WidgetListItem("widget text=" + i + "!"));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        Log.i(TAG, "StackRemoteViewsFactory onDestroy !");
        mWidgetItems.clear();
    }

    public int getCount() {
        Log.i(TAG, "StackRemoteViewsFactory getCount ： " + mWidgetItems.size());
        return mWidgetItems.size();
    }

    public RemoteViews getViewAt(int position) {
        Log.i(TAG, "StackRemoteViewsFactory getViewAt position : " + position);

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_stack_item);
        rv.setTextViewText(R.id.widget_item, "test, " + mWidgetItems.get(position).text);

        Bundle extras = new Bundle();
        extras.putInt(StackWidgetService.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

        try {
            Log.i(TAG, "StackRemoteViewsFactory Loading view " + position);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rv;
    }

    public RemoteViews getLoadingView() {
        Log.i(TAG, "StackRemoteViewsFactory getLoadingView !");
        return null;
    }

    public int getViewTypeCount() {
        Log.i(TAG, "StackRemoteViewsFactory getViewTypeCount !");
        return 1;
    }

    public long getItemId(int position) {
        Log.i(TAG, "StackRemoteViewsFactory getItemId position : "  + position);
        return position;
    }

    public boolean hasStableIds() {
        Log.i(TAG, "StackRemoteViewsFactory hasStableIds true ");
        return true;
    }

    public void onDataSetChanged() {
        Log.i(TAG, "StackRemoteViewsFactory onDataSetChanged ", new Exception());
        List<WidgetListItem> widgetItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            widgetItems.add(new WidgetListItem("widget new text=" + i * 10 + "!"));
        }
        mWidgetItems.clear();
        mWidgetItems.addAll(widgetItems);
    }
}