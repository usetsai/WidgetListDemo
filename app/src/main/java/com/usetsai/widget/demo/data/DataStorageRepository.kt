package com.usetsai.widget.demo.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorageRepository(private val context: Context) {
    val getValue: Flow<Boolean>
        get() = context.dataStore.data.map {
            it[valueKey] ?: true
        }

    suspend fun setValue(value: Boolean) {
        context.dataStore.edit { it[valueKey] = value }
    }

    val getName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[nameKey]
    }

    suspend fun setName(value: String) {
        context.dataStore.edit { preferences ->
            preferences[nameKey] = value
        }
    }

    val getNum: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[numKey] ?: 0
    }

    suspend fun setNum(value: Int) {
        context.dataStore.edit { preferences ->
            preferences[numKey] = value
        }
    }

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")
        val valueKey = booleanPreferencesKey("value")
        val nameKey = stringPreferencesKey("name")
        val numKey = intPreferencesKey("num")
    }
}