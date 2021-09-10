package com.example.smartphone_shop.repository.repositories

import com.example.smartphone_shop.repository.repositories.interfaces.DetailInfoRepository
import com.example.smartphone_shop.repository.retrofit.ApiService
import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponse
import retrofit2.Response

class DetailInfoRepositoryImpl(private val apiService: ApiService) : DetailInfoRepository {
    override suspend fun getDetailInfo(apiKey: String): Response<DetailInfoResponse> =
        apiService.getDetailInfo(apiKey)
}