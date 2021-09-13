package com.example.smartphone_shop.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartphone_shop.App
import com.example.smartphone_shop.presentation.helpers.ViewStateScreen
import com.example.smartphone_shop.repository.repositories.DetailInfoRepositoryImpl
import com.example.smartphone_shop.repository.repositories.interfaces.DetailInfoRepository
import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponseItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel : ViewModel() {

    // init CoroutineExceptionHandler
    private val errorHandler = CoroutineExceptionHandler { _, error ->
        Log.d("mainInfoResponse", error.toString())
        _viewState.postValue(ViewStateScreen(e = error))
    }

    // init LiveData
    val detailInfo: LiveData<DetailInfoResponseItem> get() = _detailInfo
    private val _detailInfo = MutableLiveData<DetailInfoResponseItem>()
    val viewState: LiveData<ViewStateScreen> get() = _viewState
    private val _viewState = MutableLiveData<ViewStateScreen>()

    // repositories
    private val detailInfoRepository: DetailInfoRepository =
        DetailInfoRepositoryImpl(App.instance.apiService)

    fun getDetailInfo(apiKey: String) {
        viewModelScope.launch(errorHandler) {
            withContext(Dispatchers.IO) {
                val detailInfoResponse = detailInfoRepository.getDetailInfo(apiKey)
                if (detailInfoResponse.isSuccessful) {
                    val detailInfoResponseItem = detailInfoResponse.body()?.get(0)
                    _detailInfo.postValue(detailInfoResponseItem)
                    _viewState.postValue(ViewStateScreen(true))
                }
            }
        }
    }
}