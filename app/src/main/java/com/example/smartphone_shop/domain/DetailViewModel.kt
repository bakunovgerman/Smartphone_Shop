package com.example.smartphone_shop.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartphone_shop.App
import com.example.smartphone_shop.repository.repositories.DetailInfoRepositoryImpl
import com.example.smartphone_shop.repository.repositories.interfaces.DetailInfoRepository
import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponseItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel() {

    // init CoroutineExceptionHandler
    private val errorHandler = CoroutineExceptionHandler { _, error ->
        Log.d("mainInfoResponse", error.toString())
    }

    // init LiveData
        // category
        // detailInfo
    val detailInfo: LiveData<DetailInfoResponseItem> get() = _detailInfo
    private val _detailInfo = MutableLiveData<DetailInfoResponseItem>()
    // repositories
    private val detailInfoRepository: DetailInfoRepository = DetailInfoRepositoryImpl(App.instance.apiService)

    fun getDetailInfo(apiKey: String) {
        viewModelScope.launch(errorHandler) {
            withContext(Dispatchers.IO) {
                val detailInfoResponse = detailInfoRepository.getDetailInfo(apiKey)
                if (detailInfoResponse.isSuccessful) {
                    val detailInfoResponseItem = detailInfoResponse.body()?.get(0)
                    _detailInfo.postValue(detailInfoResponseItem)
                }
            }
        }
    }
}