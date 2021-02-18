package com.example.news

import com.example.news.app.choosecategory.ChoseCategoryViewModel
import com.example.news.app.search.SearchViewModel
import com.example.news.app.topheadlinesnews.TopHeadlineNewsViewModel
import com.example.news.app.topheadlinesnews.TopHeadlinesPagingSource
import com.example.news.data.converters.DataModelsConverter
import com.example.news.data.implementation.NewsRepositoryImpl
import com.example.news.data.interfaces.NewsRepository
import com.example.news.network.NetworkProvider
import com.example.news.network.RetrofitProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val data = module {
    single { DataModelsConverter() }
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
    single {
        TopHeadlinesPagingSource(
            get(),
            get()
        )
    }
}

val network = module {
    single { NetworkProvider() }
    single { get<NetworkProvider>().adapterFactory }
    single { get<NetworkProvider>().gsonAdapterFactory }
    single { get<NetworkProvider>().logInterceptor }
    single { get<NetworkProvider>().authInterceptor }
    single { RetrofitProvider(get(), get(), get(), get()) }
    single { get<RetrofitProvider>().api }
}

val viewModels = module {
    viewModel {
        TopHeadlineNewsViewModel(
            get()
        )
    }
    viewModel {
        SearchViewModel(
            get(),
            get()
        )
    }
    viewModel {
        ChoseCategoryViewModel(
            get(),
            get()
        )
    }
}