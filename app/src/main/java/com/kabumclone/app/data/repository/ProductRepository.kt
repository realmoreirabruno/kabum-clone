package com.kabumclone.app.data.repository

import com.kabumclone.app.data.model.Produto
import com.kabumclone.app.data.remote.RetrofitInstance

class ProdutoRepository {
    suspend fun getProdutoPorId(id: String): Produto {
        return RetrofitInstance.api.getProdutoPorId(id)
    }
}
