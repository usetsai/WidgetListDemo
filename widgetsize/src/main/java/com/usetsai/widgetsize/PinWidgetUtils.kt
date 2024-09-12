package com.usetsai.widgetsize

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews

class PinWidgetUtils {
    companion object {
        private val TAG = PinWidgetUtils::class.java.simpleName
        fun requestPinAppWidget(context: Context, clazz: Class<*>, usePreview: Boolean) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            if (!appWidgetManager.isRequestPinAppWidgetSupported) {
                Log.i(TAG, "Pin request result: NOT SUPPORTED")
                return
            }
            try {
                val componentName = ComponentName(context, clazz)
                val intent = Intent(context, PinnedCallbackReceiver::class.java)
                val successCallback = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                    else
                        PendingIntent.FLAG_UPDATE_CURRENT
                )

                val bundle = Bundle().apply {
                    if (usePreview) {
                        val preview = RemoteViews(context.packageName, R.layout.default_widget_layout)
                        putParcelable(AppWidgetManager.EXTRA_APPWIDGET_PREVIEW, preview)
                    }
                }
                val pinResult = appWidgetManager.requestPinAppWidget(componentName, bundle, successCallback)
                Log.i(TAG, "Pin request result: RETURNED: ${pinResult.toString().uppercase()}")
            } catch (e: Exception) {
                Log.i(TAG, "Pin request result: RETURNED: EXCEPTION: $e")
            }
        }
    }
}
