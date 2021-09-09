package com.example.smartphone_shop.repository.repositories.interfaces

import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponseItem
import com.example.smartphone_shop.repository.retrofit.entities.MainInfoResponse
import retrofit2.Response

interface DetailInfoRepository {
    suspend fun getDetailInfo(apiKey: String): Response<DetailInfoResponseItem>
}