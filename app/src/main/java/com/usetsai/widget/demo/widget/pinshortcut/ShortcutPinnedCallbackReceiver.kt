package com.usetsai.widget.demo.widget.pinshortcut

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ShortcutPinnedCallbackReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "ShortcutPinnedCallbackReceiver onReceive intent:$intent")
    }

    companion object {
        private val TAG = ShortcutPinnedCallbackReceiver::class.java.simpleName
    }
}