package com.usetsai.widget.demo

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import com.usetsai.widget.demo.widget.glancewidget.NameWidget
import com.usetsai.widget.demo.widget.pinappwidget.PinWidgetMainActivity
import com.usetsai.widget.demo.widget.pinshortcut.PinShortcutUtils
import com.usetsai.widget.demo.widget.pinshortcut.ShortcutUtils
import com.usetsai.widget.demo.widget.simplewidget.ChangeWidgetPreviewProvider
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

        val changeWidgetPreviewFunction : (Int) -> Unit = { id: Int ->
            val widgetManager: AppWidgetManager = AppWidgetManager.getInstance(this)
            val views = RemoteViews(this@WidgetListActivity.packageName, R.layout.default_widget_layout)
            views.setImageViewResource(R.id.widget_image, id)
            val cm = ComponentName(this@WidgetListActivity, ChangeWidgetPreviewProvider::class.java)
            if (Build.VERSION.SDK_INT >= 35) {
                widgetManager.setWidgetPreview(cm, AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN, views)
            }
        }
        findViewById<Button>(R.id.update_widget_preview1).setOnClickListener {
            changeWidgetPreviewFunction(R.drawable.ic_test_1)
        }
        findViewById<Button>(R.id.update_widget_preview2).setOnClickListener {
            changeWidgetPreviewFunction(R.drawable.ic_test_2)
        }
        findViewById<Button>(R.id.reset_widget_preview).setOnClickListener {
            val widgetManager: AppWidgetManager = AppWidgetManager.getInstance(this)
            val cm = ComponentName(this@WidgetListActivity, ChangeWidgetPreviewProvider::class.java)
            if (Build.VERSION.SDK_INT >= 35) {
                widgetManager.removeWidgetPreview(cm, AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN)
            }
        }
    }
}