package com.catnip.ryuodaappfood.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity
import com.catnip.ryuodaappfood.data.repository.FoodRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val foodRepository: FoodRepository) : ViewModel(){
    private val _number = MutableLiveData<Int>(0)
    val number: LiveData<Int> get() = _number

    fun increment() {
        _number.postValue(_number.value?.plus(1))
    }

    fun decrement() {
        if (_number.value!! > 0){
            _number.postValue(_number.value?.minus(1))
        }
    }

    fun addItem(itemEntity : ItemEntity) = viewModelScope.launch {
        foodRepository.addItem(itemEntity)
    }
}