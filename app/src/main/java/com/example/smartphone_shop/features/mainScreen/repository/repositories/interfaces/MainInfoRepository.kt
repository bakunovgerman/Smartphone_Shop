package com.example.smartphone_shop.features.mainScreen.repository.repositories.interfaces

import com.example.smartphone_shop.features.mainScreen.repository.retrofit.entities.MainInfoResponse
import retrofit2.Response

interface MainInfoRepository {
    suspend fun getMainInfo(apiKey: String): Response<MainInfoResponse>
}