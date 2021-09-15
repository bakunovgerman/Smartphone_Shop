package com.example.smartphone_shop.features.mainScreen.repository.repositories

import com.example.smartphone_shop.features.mainScreen.repository.repositories.interfaces.MainInfoRepository
import com.example.smartphone_shop.features.mainScreen.repository.retrofit.MainApiService
import com.example.smartphone_shop.features.mainScreen.repository.retrofit.entities.MainInfoResponse
import retrofit2.Response

class MainInfoRepositoryImpl(private val mainApiService: MainApiService) : MainInfoRepository {
    override suspend fun getMainInfo(apiKey: String): Response<MainInfoResponse> =
        mainApiService.getMainInfo(apiKey)
}