package com.kabumclone.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos")
data class ProductEntity(
    @PrimaryKey val productId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val isFavorite: Boolean
)