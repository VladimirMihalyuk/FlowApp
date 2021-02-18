package com.example.news.app.choosecategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.converters.DataModelsConverter
import com.example.news.data.interfaces.NewsRepository
import com.example.news.data.models.NewsCategory
import com.example.news.network.api.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class ChoseCategoryViewModel(
    private val newsRepository: NewsRepository,
    private val converter: DataModelsConverter
    ): ViewModel() {
    private val chosenCategory = MutableSharedFlow<NewsCategory>()
    private val _listOfNews = MutableLiveData<List<ChoseCategoryPresentationModel>>()
    val listOfNews: LiveData<List<ChoseCategoryPresentationModel>>
        get() = _listOfNews

    init {
        chosenCategory
            .distinctUntilChanged()
            .onEach {
                viewModelScope.launch(Dispatchers.IO) {
                    _listOfNews.postValue(converter.convertChoseCategoryRepositoryModelToPresentation(newsRepository.getByCategory(it)))
                }
            }
            .launchIn(viewModelScope)
    }

    fun choseCategory(category: NewsCategory) {
        viewModelScope.launch {
            chosenCategory.emit(category)
        }
    }
}