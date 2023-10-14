package com.catnip.ryuodaappfood.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.catnip.ryuodaappfood.data.local.database.dao.ItemDao
import com.catnip.ryuodaappfood.data.local.database.entity.ItemEntity
import com.catnip.ryuodaappfood.utils.Constant

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemdao(): ItemDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(ctx: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class.java) {

                val instances = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java, Constant.DB_NAME
                ).build()
                instances
            }
        }
    }
}