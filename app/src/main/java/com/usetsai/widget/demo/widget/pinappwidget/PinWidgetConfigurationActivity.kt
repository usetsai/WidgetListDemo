package com.usetsai.widget.demo.widget.pinappwidget

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.usetsai.widget.demo.R

class PinWidgetConfigurationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_widget_configure)

        findViewById<Button>(R.id.configureButtonDone).setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}