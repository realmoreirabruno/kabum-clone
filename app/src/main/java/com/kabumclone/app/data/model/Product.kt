package com.kabumclone.app.data.model

data class Product(
    val productId: Int,
    val name: String,
    val description: String,
    val price: Double,
    val unit: String,
    val image: String,
    val discount: Int,
    val availability: Boolean,
    val brand: String,
    val category: String,
    val rating: Double,
    val reviews: List<Review>
)