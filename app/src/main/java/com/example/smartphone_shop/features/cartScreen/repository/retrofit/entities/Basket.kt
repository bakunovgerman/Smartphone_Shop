package com.example.smartphone_shop.features.cartScreen.repository.retrofit.entities


import com.google.gson.annotations.SerializedName

data class Basket(
    @SerializedName("images")
    val images: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
)