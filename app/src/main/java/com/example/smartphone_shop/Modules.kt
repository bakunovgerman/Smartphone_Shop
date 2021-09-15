package com.example.smartphone_shop

import android.content.Context
import com.example.smartphone_shop.features.cartScreen.domain.CartViewModel
import com.example.smartphone_shop.features.cartScreen.repository.repositories.CartRepositoryImpl
import com.example.smartphone_shop.features.cartScreen.repository.repositories.interfaces.CartRepository
import com.example.smartphone_shop.features.cartScreen.repository.retrofit.CartApiService
import com.example.smartphone_shop.features.detailsScreen.domain.DetailViewModel
import com.example.smartphone_shop.features.detailsScreen.repository.repositories.DetailInfoRepositoryImpl
import com.example.smartphone_shop.features.detailsScreen.repository.repositories.interfaces.DetailInfoRepository
import com.example.smartphone_shop.features.detailsScreen.repository.retrofit.DetailApiService
import com.example.smartphone_shop.features.mainScreen.domain.MainViewModel
import com.example.smartphone_shop.features.mainScreen.repository.data.CategoryDataSource
import com.example.smartphone_shop.features.mainScreen.repository.data.CategoryDataSourceImpl
import com.example.smartphone_shop.features.mainScreen.repository.repositories.MainInfoRepositoryImpl
import com.example.smartphone_shop.features.mainScreen.repository.repositories.interfaces.MainInfoRepository
import com.example.smartphone_shop.features.mainScreen.repository.retrofit.MainApiService
import com.example.smartphone_shop.utils.RetrofitExtensions.Companion.setClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { CartViewModel(get()) }
}

val apiServiceModule = module {
    fun provideMainApiService(retrofit: Retrofit): MainApiService {
        return retrofit.create(MainApiService::class.java)
    }
    fun provideDetailApiService(retrofit: Retrofit): DetailApiService {
        return retrofit.create(DetailApiService::class.java)
    }
    fun provideCartApiService(retrofit: Retrofit): CartApiService {
        return retrofit.create(CartApiService::class.java)
    }
    single { provideMainApiService(get()) }
    single { provideDetailApiService(get()) }
    single { provideCartApiService(get()) }
}

val retrofitModule = module {
    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .setClient()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { provideRetrofit(get()) }
}

val repositoryModule = module {
    fun provideMainInfoRepository(mainApiService: MainApiService): MainInfoRepository {
        return MainInfoRepositoryImpl(mainApiService)
    }
    fun provideDetailRepository(detailApiService: DetailApiService): DetailInfoRepository {
        return DetailInfoRepositoryImpl(detailApiService)
    }
    fun provideCartRepository(cartApiService: CartApiService): CartRepository {
        return CartRepositoryImpl(cartApiService)
    }
    fun provideCategoryDataSource(): CategoryDataSource {
        return CategoryDataSourceImpl()
    }
    single { provideMainInfoRepository(get()) }
    single { provideDetailRepository(get()) }
    single { provideCartRepository(get()) }
    single { provideCategoryDataSource() }
}

