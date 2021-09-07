package com.example.smartphone_shop.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartphone_shop.repository.data.CategoryDataSource
import com.example.smartphone_shop.repository.data.CategoryDataSourceImpl
import com.example.smartphone_shop.repository.data.CategoryDto
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    // init CoroutineExceptionHandler
    private val errorHandler = CoroutineExceptionHandler { _, error ->
        //Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
    }

    // init LiveData
    val categoryList: LiveData<List<CategoryDto>> get() = _categoryList
    private val _categoryList = MutableLiveData<List<CategoryDto>>()
    // repositories
    private val categoryDataSourceImpl: CategoryDataSource = CategoryDataSourceImpl()

    fun getCategory() {
        _categoryList.postValue(categoryDataSourceImpl.getCategory())
    }
}