package com.kabumclone.app

import android.app.Application
import com.kabumclone.app.dependencyinjection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KabumCloneApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KabumCloneApplication)
            modules(appModule)
        }
    }
}