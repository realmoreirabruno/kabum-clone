package com.kabumclone.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabumclone.app.data.model.Product
import com.kabumclone.app.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    var apiLoadedOnce = false

    private val _produtos = MutableStateFlow<List<Product>>(emptyList())
    val produtos: StateFlow<List<Product>> = _produtos.asStateFlow()

    private val _favorites = MutableStateFlow<List<Product>>(emptyList())
    val favorites: StateFlow<List<Product>> = _favorites.asStateFlow()

    fun fetchAndCacheProducts() {
        viewModelScope.launch {
            try {
                repository.fetchAndStoreProdutos()
                _produtos.value = repository.getLocalProdutos()
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Erro ao buscar e armazenar produtos", e)
            }
        }
    }

    fun loadProdutos() {
        viewModelScope.launch {
            try {
                val result = repository.getProducts()
                _produtos.value = result
                Log.d("ProductViewModel", "Products loaded: ${result.size}")
            } catch (e: Exception) {
                _produtos.value = emptyList()
                Log.e("ProductViewModel", "Error loading products: ${e.message}")
            }
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            try {
                val result = repository.getFavoriteProdutos()
                _favorites.value = result
                Log.d("ProductViewModel", "Favorites loaded: ${result.size}")
            } catch (e: Exception) {
                _favorites.value = emptyList()
                Log.e("ProductViewModel", "Error loading favorites: ${e.message}", e)
            }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            repository.toggleFavorite(product)

            val updatedList = _produtos.value.map {
                if (it.productId == product.productId) {
                    it.copy(isFavorite = !it.isFavorite)
                } else it
            }
            _produtos.value = updatedList
        }
    }
}