package com.kabumclone.app.data.local.dao

import androidx.room.*
import com.kabumclone.app.data.local.entity.ProductEntity
@Dao
interface ProductDao {
    @Query("SELECT * FROM produtos")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM produtos WHERE isFavorite = 1")
    suspend fun getFavorites(): List<ProductEntity>

    @Query("SELECT * FROM produtos WHERE productId = :id LIMIT 1")
    suspend fun getById(id: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Update
    suspend fun update(product: ProductEntity)

    @Query("DELETE FROM produtos")
    suspend fun clearAll()
}
