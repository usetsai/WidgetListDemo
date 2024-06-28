package com.usetsai.widget.demo.widget.simplewidget

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.usetsai.widget.demo.R

class SimpleWidgetConfigurationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_widget_configure)

        findViewById<Button>(R.id.configureButtonOk).setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        findViewById<Button>(R.id.configureButtonCancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}