package com.catnip.ryuodaappfood.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity
import com.catnip.ryuodaappfood.data.repository.FoodRepository
import kotlinx.coroutines.launch

class CartViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    private val _food = MutableLiveData<List<ItemEntity>>()
    val food: LiveData<List<ItemEntity>> get() = _food

    private val _harga = MutableLiveData<Int>()
    val harga: LiveData<Int> get() = _harga

    fun getALlItem() = viewModelScope.launch {
        _food.postValue(foodRepository.getAllItemCart())
    }

    fun getTotalItem() = viewModelScope.launch {
        _harga.postValue(foodRepository.getTotalItem())
    }

    fun deleteItem(foodEntity: ItemEntity) = viewModelScope.launch {
        foodRepository.removeItem(foodEntity)
    }

    fun updateStock(stock: Int, id: Int, harga: Int) = viewModelScope.launch {
        foodRepository.updateStock(stock, id, harga)
    }
}
