package com.example.news.app.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.Constants
import com.example.news.app.topheadlinesnews.TopHeadlineNewsPresentationModel
import com.example.news.app.topheadlinesnews.TopHeadlinesPagingSource
import com.example.news.data.converters.DataModelsConverter
import com.example.news.data.interfaces.NewsRepository
import com.example.news.network.api.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(
    private val newsRepository: NewsRepository,
    private val converter: DataModelsConverter
) : ViewModel() {

    private val _listOfNews =  MutableLiveData<List<SearchPresentationModel>>()
    val listOfNews: LiveData<List<SearchPresentationModel>>
        get() = _listOfNews

    fun searchByString(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _listOfNews.postValue(
                converter.convertSearchRepositoryModelToPresentation(
                    newsRepository.getByQueryString(searchString
                    )
                )
            )
        }
    }
}