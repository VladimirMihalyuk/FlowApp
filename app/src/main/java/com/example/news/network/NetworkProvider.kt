package com.example.news.network

import com.example.news.network.interceptors.AuthInterceptor
import com.example.news.network.interceptors.createLoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProvider {
    val adapterFactory: CallAdapter.Factory by lazy { CoroutineCallAdapterFactory() }

    val gsonAdapterFactory: Converter.Factory by lazy { GsonConverterFactory.create() }

    val authInterceptor: AuthInterceptor by lazy { AuthInterceptor() }

    val logInterceptor: HttpLoggingInterceptor by lazy { createLoggingInterceptor() }
}