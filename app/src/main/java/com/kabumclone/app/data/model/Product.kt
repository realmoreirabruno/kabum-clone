package com.kabumclone.app.data.model

data class Product(
    val productId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val isFavorite: Boolean
)