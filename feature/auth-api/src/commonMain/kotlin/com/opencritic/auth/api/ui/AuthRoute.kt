package com.opencritic.auth.api.ui

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object AuthRoute : Route<AuthRoute.InitArgs> by route(screenName = "auth") {
    @Serializable
    data object InitArgs
}
