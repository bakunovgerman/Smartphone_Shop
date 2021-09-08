package com.example.smartphone_shop.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartphone_shop.App
import com.example.smartphone_shop.repository.data.CategoryDataSource
import com.example.smartphone_shop.repository.data.CategoryDataSourceImpl
import com.example.smartphone_shop.repository.data.CategoryDto
import com.example.smartphone_shop.repository.repositories.MainInfoRepository
import com.example.smartphone_shop.repository.repositories.MainInfoRepositoryImpl
import com.example.smartphone_shop.repository.retrofit.entities.BestSeller
import com.example.smartphone_shop.repository.retrofit.entities.HomeStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    // init CoroutineExceptionHandler
    private val errorHandler = CoroutineExceptionHandler { _, error ->
        Log.d("mainInfoResponse", error.toString())
    }

    // init LiveData
        // category
    val categoryList: LiveData<List<CategoryDto>> get() = _categoryList
    private val _categoryList = MutableLiveData<List<CategoryDto>>()
        // mainInfo
    val homeStore: LiveData<List<HomeStore>> get() = _homeStore
    private val _homeStore = MutableLiveData<List<HomeStore>>()
    val bestSeller: LiveData<List<BestSeller>> get() = _bestSeller
    private val _bestSeller = MutableLiveData<List<BestSeller>>()
    // repositories
    private val categoryDataSourceImpl: CategoryDataSource = CategoryDataSourceImpl()
    private val mainInfoRepositoryImpl: MainInfoRepository = MainInfoRepositoryImpl(App.instance.apiService)

    fun getCategory() {
        _categoryList.postValue(categoryDataSourceImpl.getCategory())
    }

    fun getMainInfo(apiKey: String) {
        viewModelScope.launch(errorHandler) {
            withContext(Dispatchers.IO) {
                val mainInfoResponse = mainInfoRepositoryImpl.getMainInfo(apiKey)
                Log.d("mainInfoResponse", mainInfoResponse.body()!![0].homeStore.toString())
                if (mainInfoResponse.isSuccessful) {
                    val homeStoreList = mainInfoResponse.body()?.get(0)?.homeStore ?: emptyList()
                    val bestSellerList = mainInfoResponse.body()?.get(0)?.bestSeller ?: emptyList()
                    _homeStore.postValue(homeStoreList)
                    _bestSeller.postValue(bestSellerList)
                }
            }
        }
    }
}