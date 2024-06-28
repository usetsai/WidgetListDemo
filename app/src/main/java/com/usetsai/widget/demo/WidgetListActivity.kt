package com.usetsai.widget.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.usetsai.widget.demo.widget.glancewidget.NameWidget
import com.usetsai.widget.demo.widget.pinappwidget.PinWidgetMainActivity
import com.usetsai.widget.demo.widget.pinshortcut.PinShortcutUtils
import com.usetsai.widget.demo.widget.pinshortcut.ShortcutUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WidgetListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_list)

        findViewById<Button>(R.id.pin_widget).setOnClickListener {
            this@WidgetListActivity.startActivity(
                Intent(this@WidgetListActivity, PinWidgetMainActivity::class.java)
            )
        }

        findViewById<Button>(R.id.pin_shortcut).setOnClickListener {
            PinShortcutUtils.requestPinShortcut(this@WidgetListActivity)
        }

        findViewById<Button>(R.id.update_name_widget).setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch { NameWidget().changeWidgetName(this@WidgetListActivity, "newName") }
        }

        findViewById<Button>(R.id.add_shortcut).setOnClickListener {
            ShortcutUtils.testAddShortcut(this@WidgetListActivity)
        }
    }
}