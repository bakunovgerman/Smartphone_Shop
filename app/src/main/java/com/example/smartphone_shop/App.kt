package com.example.smartphone_shop

import android.app.Application
import android.content.Context
import com.example.smartphone_shop.features.cartScreen.repository.retrofit.CartApiService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(viewModelModule, apiServiceModule, retrofitModule, repositoryModule))
        }
    }
}