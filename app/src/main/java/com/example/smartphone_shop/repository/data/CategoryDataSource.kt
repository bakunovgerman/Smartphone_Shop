package com.example.smartphone_shop.repository.data

interface CategoryDataSource {
    fun getCategory(): List<CategoryDto>
}