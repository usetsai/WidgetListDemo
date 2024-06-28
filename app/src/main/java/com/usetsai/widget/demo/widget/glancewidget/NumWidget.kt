package com.usetsai.widget.demo.widget.glancewidget

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import androidx.glance.text.Text
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.usetsai.widget.demo.data.DataStorageRepository
import com.usetsai.widget.demo.data.DataStorageRepository.Companion.dataStore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlin.time.toJavaDuration

class NumWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = NumWidget()
}

class NumWidget : GlanceAppWidget() {
    suspend fun DataStore<Preferences>.loadNum() {
        updateData { prefs ->
            prefs.toMutablePreferences().apply {
                this[DataStorageRepository.numKey] = Random.Default.nextInt()
            }
        }
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        coroutineScope {
            val store = context.dataStore
            val num = store.data.map { prefs -> prefs[DataStorageRepository.numKey] }
                .stateIn(this@coroutineScope)

            if (num.value == null) store.loadNum()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    "NumWidgetWorker",
                    ExistingPeriodicWorkPolicy.KEEP,
                    PeriodicWorkRequest.Builder(
                        NumWidgetWorker::class.java,
                        15.minutes.toJavaDuration()
                    )
                        .setInitialDelay(15.minutes.toJavaDuration())
                        .build()
                )

            provideContent {
                val degrees by num.collectAsState()
                Text("Num: $degrees")
            }
        }
    }
}

class NumWidgetWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        NumWidget().apply {
            applicationContext.dataStore.loadNum()
            updateAll(applicationContext)
        }
        return Result.success()
    }
}