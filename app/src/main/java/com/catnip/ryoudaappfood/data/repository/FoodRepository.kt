package com.catnip.ryuodaappfood.data.repository

import com.catnip.ryuodaappfood.data.local.database.dao.ItemDao
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity

class FoodRepository(private val itemDao: ItemDao){

    suspend fun addItem(itemEntity: ItemEntity) = itemDao.addItem(itemEntity)

    suspend fun removeItem(itemEntity: ItemEntity) = itemDao.removeItem(itemEntity)

    suspend fun getAllItemCart(): List<ItemEntity> = itemDao.getAllItemCart()

    suspend fun getTotalItem(): Int = itemDao.getTotalItem()

    suspend fun updateStock(stock: Int, id: Int, harga: Int) = itemDao.updateStock(stock, id, harga)

    suspend fun deleteAll() = itemDao.deleteAll()
}