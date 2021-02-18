package com.example.news.data.models

data class NewsRepositoryModel (
    val author: String,
    val title: String,
    val image: String,
    val url: String,
    val time: String
)

enum class NewsCategory(val value: String) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")
}