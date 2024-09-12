package com.usetsai.widgetsize

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.util.Log

object PinShortcutUtils {
    private val TAG = PinShortcutUtils::class.java.simpleName
    fun requestPinShortcut(activity: Activity, shortcutId: String?) {
        try {
            val shortcutManager = activity.getSystemService(
                ShortcutManager::class.java
            )
            val actionIntent = Intent(Intent.ACTION_MAIN)
            actionIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            //actionIntent.setClassName(activity.getPackageName(), activity.getClass().getName());
            actionIntent.setPackage(activity.packageName)
            val shortcutInfo = ShortcutInfo.Builder(activity, shortcutId)
                .setShortLabel(activity.javaClass.simpleName)
                //.setIcon(Icon.createWithResource(activity, R.mipmap.ic_launcher_round))
                .setIcon(Icon.createWithResource(activity, R.drawable.ic_test_4))
                .setIntent(actionIntent)
                .build()
            val shortcutCallbackIntent = PendingIntent.getBroadcast(
                activity, 0,
                Intent(activity, PinnedCallbackReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
            shortcutManager.requestPinShortcut(shortcutInfo, shortcutCallbackIntent.intentSender)
            Log.e(TAG, "PinShortcutUtils add shortcut success")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "PinShortcutUtils add shortcut exception: $e")
        }
    }
}