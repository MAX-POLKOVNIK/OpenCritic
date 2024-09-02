package com.opencritic.games.details.api.ui

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object OutletReviewsRoute : Route<OutletReviewsRoute.InitArgs> by route(screenName = "outlet-reviews") {
    @Serializable
    data class InitArgs(
        val outletId: Int,
        val outletName: String,
    )
}