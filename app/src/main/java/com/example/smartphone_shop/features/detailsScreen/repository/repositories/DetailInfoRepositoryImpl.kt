package com.example.smartphone_shop.features.detailsScreen.repository.repositories

import com.example.smartphone_shop.features.detailsScreen.repository.repositories.interfaces.DetailInfoRepository
import com.example.smartphone_shop.features.detailsScreen.repository.retrofit.DetailApiService
import com.example.smartphone_shop.features.detailsScreen.repository.retrofit.entities.DetailInfoResponse
import retrofit2.Response

class DetailInfoRepositoryImpl(private val detailApiService: DetailApiService) :
    DetailInfoRepository {
    override suspend fun getDetailInfo(apiKey: String): Response<DetailInfoResponse> =
        detailApiService.getDetailInfo(apiKey)
}