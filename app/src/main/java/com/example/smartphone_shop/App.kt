package com.example.smartphone_shop

import android.app.Application
import android.content.Context
import com.example.smartphone_shop.repository.retrofit.ApiService

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        App.applicationContext = applicationContext
    }

    init {
        instance = this
    }

    val apiService = ApiService.create()

    companion object {
        lateinit var instance: App
            private set

        lateinit var applicationContext: Context
            private set
    }
}