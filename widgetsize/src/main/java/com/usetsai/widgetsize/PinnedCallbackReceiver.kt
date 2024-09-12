package com.usetsai.widgetsize

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class PinnedCallbackReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "ShortcutPinnedCallbackReceiver onReceive intent:$intent")
    }

    companion object {
        private val TAG = PinnedCallbackReceiver::class.java.simpleName
    }
}