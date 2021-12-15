package com.test.jetback

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.first

class SaveSystem(context: Context)
{
    private var dataStore: DataStore<Preferences> = context.createDataStore("uriAddres")

    suspend fun save(key: String, value: String)
    {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { setting ->
            setting[dataStoreKey] = value
        }
    }

    suspend fun read(key: String): String?
    {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}

