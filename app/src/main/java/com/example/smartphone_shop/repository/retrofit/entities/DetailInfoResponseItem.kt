package com.example.smartphone_shop.repository.retrofit.entities


import com.google.gson.annotations.SerializedName

data class DetailInfoResponseItem(
    @SerializedName("CPU")
    val cPU: String,
    @SerializedName("camera")
    val camera: String,
    @SerializedName("capacity")
    val capacity: List<String>,
    @SerializedName("color")
    val color: List<String>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("is_favorites")
    val isFavorites: Boolean,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("sd")
    val sd: String,
    @SerializedName("ssd")
    val ssd: String,
    @SerializedName("title")
    val title: String
)