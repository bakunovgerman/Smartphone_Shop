package com.example.smartphone_shop.repository.repositories

import com.example.smartphone_shop.repository.repositories.interfaces.CartRepository
import com.example.smartphone_shop.repository.repositories.interfaces.DetailInfoRepository
import com.example.smartphone_shop.repository.retrofit.ApiService
import com.example.smartphone_shop.repository.retrofit.entities.CartResponse
import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponse
import retrofit2.Response

class CartRepositoryImpl(private val apiService: ApiService) : CartRepository {

    override suspend fun getCart(apiKey: String): Response<CartResponse> =
        apiService.getCart(apiKey)

}