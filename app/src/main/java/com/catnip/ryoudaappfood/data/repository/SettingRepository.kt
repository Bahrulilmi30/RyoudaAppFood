package com.catnip.ryuodaappfood.data.repository

import com.catnip.ryuodaappfood.data.local.datastore.SettingDataStore
import kotlinx.coroutines.flow.Flow

class SettingRepository(private val settingDataStoreManager: SettingDataStore) {
    val getSetting: Flow<Boolean> = settingDataStoreManager.getSetting

    suspend fun setSetting(condition: Boolean) = settingDataStoreManager.setSetting(condition)
}