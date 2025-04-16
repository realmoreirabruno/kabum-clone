package com.kabumclone.app.dependencyinjection

import com.kabumclone.app.data.repository.ProductRepository
import com.kabumclone.app.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.kabumclone.app.data.remote.ApiService

val appModule = module {
    single {
        try {
            Retrofit.Builder()
                .baseUrl("https://fake-store-api.mock.beeceptor.com") // Mock API for testing
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .also { Log.d("Koin", "Retrofit created successfully") }
        } catch (e: Exception) {
            Log.e("Koin", "Failed to create Retrofit: ${e.message}")
            throw e
        }
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
            .also { Log.d("Koin", "ProductApi created successfully") }
    }
    single {
        ProductRepository(get())
            .also { Log.d("Koin", "ProductRepository created successfully") }
    }
    viewModel {
        ProductViewModel(get())
            .also { Log.d("Koin", "ProductViewModel created successfully") }
    }
}