package com.usetsai.widget.demo.widget.simplewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.usetsai.widget.demo.R
import com.usetsai.widget.demo.WidgetListActivity

open class SimpleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.default_widget_layout)
            appWidgetManager.updateAppWidget(appWidgetId, views)

            val intent = Intent(context, WidgetListActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val resultPendingIntent: PendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
            views.setOnClickPendingIntent(R.id.widget_item_container, resultPendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

class WidgetProvider1x1 : SimpleWidgetProvider()
class WidgetProvider2x1 : SimpleWidgetProvider()
class WidgetProvider2x2 : SimpleWidgetProvider()
class WidgetProvider3x2 : SimpleWidgetProvider()
class WidgetProvider4x2 : SimpleWidgetProvider()
class WidgetProvider4x4 : SimpleWidgetProvider()
class WidgetProvider5x2 : SimpleWidgetProvider()