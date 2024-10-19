package com.opencritic.games.details.api.ui

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object AuthorReviewsRoute : Route<AuthorReviewsRoute.InitArgs> by route(screenName = "author-reviews") {
    @Serializable
    data class InitArgs(
        val authorId: Int,
        val authorName: String,
    )
}