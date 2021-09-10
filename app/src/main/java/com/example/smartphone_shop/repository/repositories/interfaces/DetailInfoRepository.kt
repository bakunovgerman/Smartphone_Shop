package com.example.smartphone_shop.repository.repositories.interfaces

import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponse
import retrofit2.Response

interface DetailInfoRepository {
    suspend fun getDetailInfo(apiKey: String): Response<DetailInfoResponse>
}