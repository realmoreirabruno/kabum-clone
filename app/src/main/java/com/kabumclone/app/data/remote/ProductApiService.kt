package com.kabumclone.app.data.remote

import com.kabumclone.app.data.model.Produto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("produtos/{id}")
    suspend fun getProdutoPorId(@Path("id") id: String): Produto
}
