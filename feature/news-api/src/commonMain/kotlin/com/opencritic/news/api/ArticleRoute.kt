package com.opencritic.news.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object ArticleRoute : Route<ArticleRoute.InitArgs> by route(screenName = "article") {
    @Serializable
    data class InitArgs(
        val articleId: Long,
        val title: String,
    )
}