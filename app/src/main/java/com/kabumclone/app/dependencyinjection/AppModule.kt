package com.kabumclone.app.dependencyinjection

import com.kabumclone.app.data.repository.ProductRepository
import com.kabumclone.app.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import androidx.room.Room
import com.kabumclone.app.data.local.database.AppDatabase
import com.kabumclone.app.data.local.database.MIGRATION_1_2
import com.kabumclone.app.data.remote.ApiService
import org.koin.android.ext.koin.androidApplication

val appModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "kabum_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
            .also { Log.d("Koin", "AppDatabase created successfully") }
    }
    single { get<AppDatabase>().productDao() }
        .also { Log.d("Koin", "ProductDao created successfully") }
    single {
        try {
            Retrofit.Builder()
                .baseUrl("https://fake-store-api.mock.beeceptor.com")
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
            .also { Log.d("Koin", "ApiService created successfully") }
    }
    single {
        ProductRepository(get(), get())
            .also { Log.d("Koin", "ProductRepository created successfully") }
    }
    viewModel {
        ProductViewModel(get())
            .also { Log.d("Koin", "ProductViewModel created successfully") }
    }
}

