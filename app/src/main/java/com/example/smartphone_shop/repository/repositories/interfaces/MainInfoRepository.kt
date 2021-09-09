package com.example.smartphone_shop.repository.repositories.interfaces

import com.example.smartphone_shop.repository.retrofit.entities.MainInfoResponse
import retrofit2.Response

interface MainInfoRepository {
    suspend fun getMainInfo(apiKey: String): Response<MainInfoResponse>
}