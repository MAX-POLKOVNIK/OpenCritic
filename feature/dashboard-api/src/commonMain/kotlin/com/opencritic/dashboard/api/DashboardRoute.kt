package com.opencritic.dashboard.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object DashboardRoute : Route<DashboardRoute.InitArgs> by route("dashboard") {
    @Serializable
    data object InitArgs
}