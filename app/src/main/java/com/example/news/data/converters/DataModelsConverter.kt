package com.example.news.data.converters

import com.example.news.Constants.DAY
import com.example.news.Constants.HOUR
import com.example.news.Constants.MINUTE
import com.example.news.Constants.YEAR
import com.example.news.app.choosecategory.ChoseCategoryPresentationModel
import com.example.news.app.search.SearchPresentationModel
import com.example.news.app.topheadlinesnews.TopHeadlineNewsPresentationModel
import com.example.news.data.models.NewsRepositoryModel
import com.example.news.network.models.News
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlin.time.ExperimentalTime


class DataModelsConverter {
    fun convertNetworkNewsModelToRepository(networkModel: News): List<NewsRepositoryModel> {
        val list = mutableListOf<NewsRepositoryModel>()
        for (item in networkModel.articles) {
            list.add(NewsRepositoryModel(
                "${item.source?.name}",
                "${item.title}",
                "${item.urlToImage}",
                "${item.url}",
                getDifInTime(item.publishedAt!!)))
        }
        return list
    }

    fun convertRepositoryModelToPresentation(list: List<NewsRepositoryModel>): List<TopHeadlineNewsPresentationModel> =
        list.map {
            TopHeadlineNewsPresentationModel(
                it.author,
                it.title,
                it.image,
                it.url,
                it.time
            )
        }

    fun convertSearchRepositoryModelToPresentation(list: List<NewsRepositoryModel>): List<SearchPresentationModel> =
        list.map {
            SearchPresentationModel(
                it.author,
                it.title,
                it.image,
                it.url,
                it.time
            )
        }

    fun convertChoseCategoryRepositoryModelToPresentation(list: List<NewsRepositoryModel>): List<ChoseCategoryPresentationModel> =
        list.map {
            ChoseCategoryPresentationModel(
                it.author,
                it.title,
                it.image,
                it.url,
                it.time
            )
        }


    @OptIn(ExperimentalTime::class)
    private fun getDifInTime(timeString: String): String {
        val timeZone = TimeZone.of("Europe/London")

        val currentTime = Clock.System.now()
        val time = Instant.parse(timeString)
        val dif = currentTime - time

        val milliSeconds = dif.inMilliseconds

        if(milliSeconds < HOUR) {
            val minutes = (milliSeconds / MINUTE).toInt()
            if (minutes == 1) {
                return "$minutes minute"
            } else {
                return "$minutes minutes"
            }
        } else {
            if(milliSeconds < DAY) {
                val hours = (milliSeconds / HOUR).toInt()
                if (hours == 1) {
                    return "$hours hour"
                } else {
                    return "$hours hours"
                }
            } else {
                if (milliSeconds < YEAR) {
                    val day = (milliSeconds / DAY).toInt()
                    if (day == 1) {
                        return "$day day"
                    } else {
                        return "$day days"
                    }
                } else {
                    return "more than 1 year"
                }
            }
        }
    }
}