package com.kabumclone.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabumclone.app.data.model.Product
import com.kabumclone.app.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {
//    private val repository = ProductRepository()
//
//    private val _produto = MutableStateFlow<Product?>(null)
//    val produto: StateFlow<Product?> = _produto
//
//    fun carregarProduto(id: String) {
//        viewModelScope.launch {
//            try {
//                _produto.value = repository.getProdutoPorId(id)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}
