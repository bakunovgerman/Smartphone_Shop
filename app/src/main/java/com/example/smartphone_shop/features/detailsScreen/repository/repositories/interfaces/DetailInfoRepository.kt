package com.example.smartphone_shop.features.detailsScreen.repository.repositories.interfaces

import com.example.smartphone_shop.features.detailsScreen.repository.retrofit.entities.DetailInfoResponse
import retrofit2.Response

interface DetailInfoRepository {
    suspend fun getDetailInfo(apiKey: String): Response<DetailInfoResponse>
}