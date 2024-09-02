package com.opencritic.navigation

import kotlinx.serialization.Serializable

data object ShareLinkRoute : Route<ShareLinkRoute.InitArgs> by route("share-link") {
    @Serializable
    data class InitArgs(val url: String)
}

fun String.asShareLinkRouteArgs(): ShareLinkRoute.InitArgs =
    ShareLinkRoute.InitArgs(url = this)