package com.kabumclone.app.data.repository

import android.util.Log
import com.kabumclone.app.data.local.dao.ProductDao
import com.kabumclone.app.data.local.entity.ProductEntity
import com.kabumclone.app.data.model.Product
import com.kabumclone.app.data.remote.ApiService

class ProductRepository(private val api: ApiService,
                        private val dao: ProductDao) {
    suspend fun getProducts(): List<Product> {
        // Mock
//        return listOf(
//            Product(1, "Product 1", "Description 1", 100.0),
//            Product(2, "Product 2", "Description 2", 200.0),
//            Product(3, "Product 3", "Description 3", 300.0)
//        )
        Log.d("Repository", "API returned ${api.getProducts().size} products")
         return api.getProducts()
    }

    suspend fun fetchAndStoreProdutos() {
        try {
            val response = api.getProducts()

            response.forEach { product ->
                val existing = dao.getById(product.productId)
                val entity = ProductEntity(
                    productId = product.productId,
                    name = product.name,
                    description = product.description,
                    price = product.price,
                    image = product.image,
                    isFavorite = existing?.isFavorite ?: false
                )
                dao.insert(entity)
            }

        } catch (e: Exception) {
            Log.e("ProductRepository", "Erro ao buscar produtos: ${e.message}")
            throw e
        }
    }

    suspend fun getLocalProdutos(): List<Product> {
        return dao.getAll().map {
            Product(
                productId = it.productId,
                name = it.name,
                description = it.description,
                price = it.price,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }
    }

    suspend fun getFavoriteProdutos(): List<Product> {
        return dao.getFavorites().map {
            Product(
                productId = it.productId,
                name = it.name,
                description = it.description,
                price = it.price,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }
    }

    suspend fun toggleFavorite(product: Product) {
        val entity = dao.getById(product.productId)
        if (entity != null) {
            val updatedEntity = entity.copy(isFavorite = !entity.isFavorite)
            dao.update(updatedEntity)
            Log.d(
                "ProductRepository",
                "Toggled favorite for ${product.name} to ${updatedEntity.isFavorite}"
            )
        }
    }
}
