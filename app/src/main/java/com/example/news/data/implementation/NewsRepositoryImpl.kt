package com.example.news.data.implementation

import com.example.news.Constants
import com.example.news.data.converters.DataModelsConverter
import com.example.news.data.interfaces.NewsRepository
import com.example.news.data.models.NewsCategory
import com.example.news.data.models.NewsRepositoryModel
import com.example.news.network.api.NewsApi

class NewsRepositoryImpl (
    private val api: NewsApi,
    private val converter: DataModelsConverter
    ) : NewsRepository {

    override suspend fun getTopHeadlinesNews(page: Int): List<NewsRepositoryModel> =
        converter.convertNetworkNewsModelToRepository( api.getTopHeadlines(page, Constants.PAGE_SIZE))

    override suspend fun getByQueryString(queryString: String): List<NewsRepositoryModel>  =
        converter.convertNetworkNewsModelToRepository( api.getByKeyWord(queryString))

    override suspend fun getByCategory(category: NewsCategory): List<NewsRepositoryModel> =
        converter.convertNetworkNewsModelToRepository( api.getByCategory(category.value))


}