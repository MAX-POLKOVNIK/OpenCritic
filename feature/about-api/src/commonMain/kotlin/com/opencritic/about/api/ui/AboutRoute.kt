package com.opencritic.about.api.ui

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object AboutRoute : Route<AboutRoute.InitArgs> by route(screenName = "about") {
    @Serializable
    data object InitArgs
}