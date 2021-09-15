package com.example.smartphone_shop.features.mainScreen.repository.data

interface CategoryDataSource {
    fun getCategory(): List<CategoryDto>
}