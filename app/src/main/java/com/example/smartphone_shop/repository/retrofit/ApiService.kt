package com.example.smartphone_shop.repository.retrofit

import com.example.smartphone_shop.repository.retrofit.entities.MainInfoResponse
import com.example.smartphone_shop.repository.retrofit.utils.RetrofitExtensions.Companion.setClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    // методы
    @GET("main")
    suspend fun getMainInfo(@Header("x-apikey") apiKey: String): Response<MainInfoResponse>

    companion object {
        private const val BASE_URL = "https://db2021ecom-edca.restdb.io/rest/"

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

    }

}