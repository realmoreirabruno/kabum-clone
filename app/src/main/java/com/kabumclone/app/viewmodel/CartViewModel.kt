package com.kabumclone.app.viewmodel

import androidx.lifecycle.ViewModel
import com.kabumclone.app.data.model.CartItem
import com.kabumclone.app.data.model.Produto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun addToCart(produto: Produto) {
        val currentList = _cartItems.value.toMutableList()
        val existing = currentList.find { it.id == produto.id }

        if (existing != null) {
            val updated = existing.copy(quantity = existing.quantity + 1)
            currentList[currentList.indexOf(existing)] = updated
        } else {
            currentList.add(
                CartItem(
                    id = produto.id,
                    nome = produto.nome,
                    preco = produto.preco,
                    imagemUrl = produto.imagemUrl,
                    quantity = 1
                )
            )
        }

        _cartItems.value = currentList
    }

    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.preco * it.quantity }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
