package com.example.news.network.api


import com.example.news.network.models.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi{
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("page")page: Int = 1, @Query("pageSize")pageSize: Int = 15, @Query("country")country: String = "gb"): News

    @GET("everything")
    suspend fun getByKeyWord(@Query("q")query: String, @Query("language")language: String = "en", @Query("pageSize")pageSize: Int = 30): News

    @GET("top-headlines")
    suspend fun getByCategory(@Query("category")category: String, @Query("page")page: Int = 1, @Query("pageSize")pageSize: Int = 30, @Query("country")country: String = "gb"): News
}

