package com.example.news.app.topheadlinesnews

import androidx.paging.PagingSource
import com.example.news.data.converters.DataModelsConverter
import com.example.news.data.interfaces.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopHeadlinesPagingSource(
    val newsRepository: NewsRepository,
    val dataModelsConverter: DataModelsConverter
    ) : PagingSource<Int, TopHeadlineNewsPresentationModel>() {

    private var listener: (suspend () -> Unit)? = null
    fun setListener(listener: suspend () -> Unit) {
        this.listener = listener
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopHeadlineNewsPresentationModel> {
        try {
            return withContext(Dispatchers.IO) {
                val nextPage = params.key ?: 1
                val response = dataModelsConverter.convertRepositoryModelToPresentation(newsRepository.getTopHeadlinesNews(nextPage))

                listener?.invoke()
                return@withContext LoadResult.Page(
                        data = response,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = nextPage + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }


}
