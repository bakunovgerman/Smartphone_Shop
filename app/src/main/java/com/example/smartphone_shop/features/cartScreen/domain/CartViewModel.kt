package com.example.smartphone_shop.features.cartScreen.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartphone_shop.App
import com.example.smartphone_shop.features.cartScreen.repository.repositories.CartRepositoryImpl
import com.example.smartphone_shop.features.cartScreen.repository.repositories.interfaces.CartRepository
import com.example.smartphone_shop.features.cartScreen.repository.retrofit.entities.CartResponseItem
import com.example.smartphone_shop.helpers.ViewStateScreen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {

    // init CoroutineExceptionHandler
    private val errorHandler = CoroutineExceptionHandler { _, error ->
        Log.d("mainInfoResponse", error.toString())
        _viewState.postValue(ViewStateScreen(e = error))
    }

    // init LiveData
    val cartInfo: LiveData<CartResponseItem> get() = _cartInfo
    private val _cartInfo = MutableLiveData<CartResponseItem>()
    val viewState: LiveData<ViewStateScreen> get() = _viewState
    private val _viewState = MutableLiveData<ViewStateScreen>()

    fun getDetailInfo(apiKey: String) {
        viewModelScope.launch(errorHandler) {
            withContext(Dispatchers.IO) {
                val cartResponse = cartRepository.getCart(apiKey)
                if (cartResponse.isSuccessful) {
                    val cartResponseItem = cartResponse.body()?.get(0)
                    _cartInfo.postValue(cartResponseItem)
                    _viewState.postValue(ViewStateScreen(true))
                }
            }
        }
    }
}