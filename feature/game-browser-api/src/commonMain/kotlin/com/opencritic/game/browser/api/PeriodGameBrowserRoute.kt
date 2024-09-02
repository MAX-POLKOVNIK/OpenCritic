package com.opencritic.game.browser.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object PeriodGameBrowserRoute : Route<PeriodGameBrowserRoute.InitArgs> by route(screenName = "period-game-browser") {
    @Serializable
    data class InitArgs(
        val period: Period,
    ) {
        @Serializable
        enum class Period {
            ReviewedThisWeek,
            RecentlyReleased,
            UpcomingReleases,
        }
    }
}