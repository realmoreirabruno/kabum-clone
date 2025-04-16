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
    private val _produtos = MutableStateFlow<List<Product>>(emptyList())
    val produtos: StateFlow<List<Product>> = _produtos.asStateFlow()

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
}