package com.kabumclone.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kabumclone.app.data.local.dao.ProductDao
import com.kabumclone.app.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProductDao
}
