package com.kabumclone.app.data.remote

import com.kabumclone.app.data.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("/api/products")
    suspend fun getProducts(): List<Product>
}
