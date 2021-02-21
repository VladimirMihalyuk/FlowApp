package com.example.news.app.topheadlinesnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.*
import timber.log.Timber


class TopHeadlineNewsViewModel(
    private val pagingSource: TopHeadlinesPagingSource
    ) : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    val news: Flow<PagingData<TopHeadlineNewsPresentationModel>> = Pager(PagingConfig(PAGE_SIZE)) {
        pagingSource
    }.flow
        .cachedIn(viewModelScope)
        .also {
            pagingSource.setListener {
                _isLoading.emit(false)
            }
        }
}