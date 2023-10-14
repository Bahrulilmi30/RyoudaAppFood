package com.catnip.ryuodaappfood.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity

@Dao
interface ItemDao {
    @Insert
    suspend fun addItem(itemEntity: ItemEntity)

    @Delete
    suspend fun removeItem(itemEntity: ItemEntity)

    @Query("SELECT * FROM food ORDER BY id DESC")
    suspend fun getAllItemCart(): List<ItemEntity>

    @Query("SELECT SUM(harga) from food ")
    suspend fun getTotalItem(): Int

    @Query("UPDATE food SET stock = :stock, harga = :harga WHERE id = :id ")
    suspend fun updateStock(stock: Int, id: Int, harga: Int)

    @Query("DELETE FROM food")
    suspend fun deleteAll()
}