package com.catnip.ryuodaappfood.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.catnip.ryuodaappfood.data.repository.SettingRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val settingRepository: SettingRepository
) : ViewModel() {
    val getSetting = settingRepository.getSetting.asLiveData()

    fun setSetting(condition: Boolean) = viewModelScope.launch {
        settingRepository.setSetting(condition)
    }
}