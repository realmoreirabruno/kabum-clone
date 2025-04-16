package com.kabumclone.app.data.repository

import com.kabumclone.app.data.model.Product
import com.kabumclone.app.data.remote.ApiService

class ProductRepository(private val api: ApiService) {
    suspend fun getProducts(): List<Product> {
        // Mock data to test UI
//        return listOf(
//            Product(1, "Product 1", "Description 1", 100.0),
//            Product(2, "Product 2", "Description 2", 200.0),
//            Product(3, "Product 3", "Description 3", 300.0)
//        )
        // Uncomment to use real API
         return api.getProducts()
    }
}
