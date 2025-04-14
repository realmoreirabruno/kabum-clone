package com.kabumclone.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabumclone.app.data.model.Produto
import com.kabumclone.app.data.repository.ProdutoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {
    private val repository = ProdutoRepository()

    private val _produto = MutableStateFlow<Produto?>(null)
    val produto: StateFlow<Produto?> = _produto

    fun carregarProduto(id: String) {
        viewModelScope.launch {
            try {
                _produto.value = repository.getProdutoPorId(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
