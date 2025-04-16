package com.kabumclone.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kabumclone.app.data.local.dao.ProductDao
import com.kabumclone.app.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}