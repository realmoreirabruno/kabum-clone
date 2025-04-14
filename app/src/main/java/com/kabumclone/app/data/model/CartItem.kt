package com.kabumclone.app.data.model

data class CartItem(
    val id: String,
    val nome: String,
    val preco: Double,
    val imagemUrl: String,
    var quantity: Int = 1
)
