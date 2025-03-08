package com.example.aiva.core.di

import android.app.Application
import com.example.aiva.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AivaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AivaApplication)
            modules(appModule)
        }
    }
}
