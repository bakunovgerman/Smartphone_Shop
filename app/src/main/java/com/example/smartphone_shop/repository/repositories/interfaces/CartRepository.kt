package com.example.smartphone_shop.repository.repositories.interfaces

import com.example.smartphone_shop.repository.retrofit.entities.CartResponse
import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponse
import retrofit2.Response

interface CartRepository {
    suspend fun getCart(apiKey: String): Response<CartResponse>
}