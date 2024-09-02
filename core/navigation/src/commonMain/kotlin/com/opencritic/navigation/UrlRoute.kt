package com.opencritic.navigation

import kotlinx.serialization.Serializable

data object UrlRoute : Route<UrlRoute.InitArgs> by route(screenName = "external-url") {
    @Serializable
    data class InitArgs(
        val url: String,
    )
}

fun String.asUrlRouteArgs(): UrlRoute.InitArgs =
    UrlRoute.InitArgs(url = this)