package com.usetsai.widget.demo.widget.blurwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.usetsai.widget.demo.R;

public class WidgetBlurLayoutTest4x2 extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_blur_layout_4x2);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}