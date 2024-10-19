package com.opencritic.news.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object ArticleListRoute : Route<ArticleListRoute.InitArgs> by route(screenName = "news") {
    @Serializable
    data object InitArgs
}