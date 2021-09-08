package com.example.smartphone_shop.repository.retrofit.entities


import com.google.gson.annotations.SerializedName

data class MainInfoResponseItem(
    @SerializedName("best_seller")
    val bestSeller: List<BestSeller>,
    @SerializedName("home_store")
    val homeStore: List<HomeStore>,
    @SerializedName("_id")
    val id2: String
)