package com.kabumclone.app.data.local.dao

import androidx.room.*
import com.kabumclone.app.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM produtos")
    suspend fun getAll(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(produtos: List<ProductEntity>)

    @Query("DELETE FROM produtos")
    suspend fun clearAll()
}
