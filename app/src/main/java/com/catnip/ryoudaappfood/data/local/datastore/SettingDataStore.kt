package com.catnip.ryuodaappfood.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.catnip.ryuodaappfood.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingDataStore(private val context: Context) {
    val getSetting: Flow<Boolean> = context.dataStore.data.map{
        it[SETTINGS_THEME_KEY] ?: false
    }

    suspend fun setSetting(condition: Boolean) {
        context.dataStore.edit {
            it[SETTINGS_THEME_KEY] = condition
        }
    }

    companion object {
        private val SETTINGS_THEME_KEY = booleanPreferencesKey(Constant.SETTINGS_KEY)
        private val Context.dataStore by preferencesDataStore(
            name = Constant.DATASTORE_NAME
        )
    }
}