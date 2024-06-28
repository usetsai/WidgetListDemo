package com.usetsai.widget.demo.widget.pinappwidget

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.usetsai.widget.demo.R

class PinWidgetProvider : AppWidgetProvider() {
    @SuppressLint("RemoteViewLayout")
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_pin_widget_layout)
            views.setTextViewText(R.id.widget_text, "I AM $appWidgetId")
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}