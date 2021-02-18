package com.example.news.app.topheadlinesnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow


class TopHeadlineNewsViewModel(
    private val pagingSource: TopHeadlinesPagingSource
    ) : ViewModel() {

    val movies: Flow<PagingData<TopHeadlineNewsPresentationModel>> = Pager(PagingConfig(PAGE_SIZE)) {
        pagingSource
    }.flow
        .cachedIn(viewModelScope)

}