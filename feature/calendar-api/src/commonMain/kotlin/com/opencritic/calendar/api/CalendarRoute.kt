package com.opencritic.calendar.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object CalendarRoute : Route<CalendarRoute.InitArgs> by route(screenName = "calendar") {
    @Serializable
    data object InitArgs
}