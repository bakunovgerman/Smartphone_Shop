package com.example.smartphone_shop.repository.repositories

import com.example.smartphone_shop.repository.retrofit.ApiService
import com.example.smartphone_shop.repository.retrofit.entities.MainInfoResponse
import retrofit2.Response

class MainInfoRepositoryImpl(private val apiService: ApiService) : MainInfoRepository {
    override suspend fun getMainInfo(apiKey: String): Response<MainInfoResponse> =
        apiService.getMainInfo(apiKey)
}