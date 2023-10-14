package com.catnip.ryoudaappfood.ui.confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity
import com.catnip.ryuodaappfood.data.repository.FoodRepository
import kotlinx.coroutines.launch

class ConfirmViewModel(private val foodRepository: FoodRepository): ViewModel(){
    private val _item = MutableLiveData<List<ItemEntity>>()
    val item: LiveData<List<ItemEntity>> get() = _item

    fun getAllItem() = viewModelScope.launch {
        _item.postValue(foodRepository.getAllItemCart())
    }
    fun deleteItem(itemEntity: ItemEntity) = viewModelScope.launch {
        foodRepository.removeItem(itemEntity)
    }

    fun deleteALl() = viewModelScope.launch {
        foodRepository.deleteAll()
    }
}