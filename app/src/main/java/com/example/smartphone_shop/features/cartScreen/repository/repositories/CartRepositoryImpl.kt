package com.example.smartphone_shop.features.cartScreen.repository.repositories

import com.example.smartphone_shop.features.cartScreen.repository.repositories.interfaces.CartRepository
import com.example.smartphone_shop.features.cartScreen.repository.retrofit.CartApiService
import com.example.smartphone_shop.features.cartScreen.repository.retrofit.entities.CartResponse
import retrofit2.Response

class CartRepositoryImpl(private val cartApiService: CartApiService) : CartRepository {

    override suspend fun getCart(apiKey: String): Response<CartResponse> =
        cartApiService.getCart(apiKey)

}