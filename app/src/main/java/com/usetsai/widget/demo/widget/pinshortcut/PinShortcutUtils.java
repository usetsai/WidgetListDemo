package com.usetsai.widget.demo.widget.pinshortcut;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.util.Log;

import com.usetsai.widget.demo.R;

public class PinShortcutUtils {
    private static final String TAG = PinShortcutUtils.class.getSimpleName();
    public static void requestPinShortcut(Activity activity) {
        try {
            final ShortcutManager shortcutManager = activity.getSystemService(ShortcutManager.class);

            Intent actionIntent = new Intent(Intent.ACTION_MAIN);
            actionIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            //actionIntent.setClassName(activity.getPackageName(), activity.getClass().getName());
            actionIntent.setPackage(activity.getPackageName());
            actionIntent.putExtra("duplicate", false);

            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(activity, "test_shortcut_id")
                    .setShortLabel(activity.getClass().getSimpleName())
                    .setIcon(Icon.createWithResource(activity, R.drawable.ic_android_round))
                    .setIntent(actionIntent)
                    .build();

            PendingIntent shortcutCallbackIntent =  PendingIntent.getBroadcast(activity, 0,
                    new Intent(activity, ShortcutPinnedCallbackReceiver.class),
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
            shortcutManager.requestPinShortcut(shortcutInfo, shortcutCallbackIntent.getIntentSender());
            Log.e(TAG, "add shortcut success");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "add shortcut exception: " + e);
        }
    }
}