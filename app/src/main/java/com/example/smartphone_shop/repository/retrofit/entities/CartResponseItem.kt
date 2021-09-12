package com.example.smartphone_shop.repository.retrofit.entities


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