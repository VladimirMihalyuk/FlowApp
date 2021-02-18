package com.example.news.data.interfaces

import com.example.news.data.models.NewsCategory
import com.example.news.data.models.NewsRepositoryModel

interface NewsRepository {
    suspend fun getTopHeadlinesNews(page: Int): List<NewsRepositoryModel>

    suspend fun getByQueryString(queryString: String): List<NewsRepositoryModel>

    suspend fun getByCategory(category: NewsCategory): List<NewsRepositoryModel>
}