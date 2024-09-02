package com.opencritic.search.api.ui

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object SearchRoute : Route<SearchRoute.InitArgs> by route("search") {
    @Serializable
    data object InitArgs
}