package com.example.smartphone_shop.features.cartScreen.repository.repositories.interfaces

import com.example.smartphone_shop.features.cartScreen.repository.retrofit.entities.CartResponse
import retrofit2.Response

interface CartRepository {
    suspend fun getCart(apiKey: String): Response<CartResponse>
}