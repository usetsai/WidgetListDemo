package com.usetsai.widget.demo.widget.glancewidget

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import androidx.glance.text.Text
import com.usetsai.widget.demo.data.DataStorageRepository
import com.usetsai.widget.demo.data.DataStorageRepository.Companion.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NameWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NameWidget()
}

class NameWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val store = context.dataStore
        val initial = store.data.first()

        provideContent {
            val data by store.data.collectAsState(initial)
            val scope = rememberCoroutineScope()
            Text(
                text = "Hello ${data[DataStorageRepository.nameKey]}",
                modifier =
                GlanceModifier.clickable {
                    scope.launch {
                        store.updateData {
                            it.toMutablePreferences().apply { set(DataStorageRepository.nameKey, "Changed") }
                        }
                    }
                }
            )
        }
    }

    suspend fun changeWidgetName(context: Context, newName: String) {
        context.dataStore.updateData {
            it.toMutablePreferences().apply { set(DataStorageRepository.nameKey, newName) }
        }
        NameWidget().updateAll(context)
    }
}