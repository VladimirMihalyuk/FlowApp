package com.example.news.network

import com.example.news.network.api.NewsApi
import com.example.news.network.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitProvider(
        private val authInterceptor: AuthInterceptor,
        private val loggingInterceptor: HttpLoggingInterceptor,
        private val converterFactory: Converter.Factory,
        private val callAdapterFactory: CallAdapter.Factory
) {
    private val httpClient: OkHttpClient by lazy {
           OkHttpClient().newBuilder()
               .addInterceptor(authInterceptor)
               .addInterceptor(loggingInterceptor)
               .build()
    }

    val api: NewsApi by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(NewsApi::class.java)
    }
}