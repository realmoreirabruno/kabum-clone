package com.kabumclone.app.data.local.provider

import android.content.Context
import androidx.room.Room
import com.kabumclone.app.data.local.database.AppDatabase

object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "kabum_clone_db"
            ).build()
        }
        return INSTANCE!!
    }
}
