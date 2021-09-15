package com.example.smartphone_shop.features.cartScreen.repository.retrofit.entities


import com.example.smartphone_shop.features.cartScreen.repository.retrofit.entities.Basket
import com.google.gson.annotations.SerializedName

data class CartResponseItem(
    @SerializedName("basket")
    val basket: List<Basket>,
    @SerializedName("Delivery")
    val delivery: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("total")
    val total: Int
)