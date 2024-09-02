package com.opencritic.halloffame.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object HallOfFameRoute : Route<HallOfFameRoute.InitArgs> by route(screenName = "hall-of-fame") {
    @Serializable
    data object InitArgs
}