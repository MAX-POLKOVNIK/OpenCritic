package com.opencritic.games.details.api.ui

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object GameDetailsRoute : Route<GameDetailsRoute.InitArgs> by route(screenName = "game") {
    @Serializable
    data class InitArgs(
        val gameId: Long,
        val gameName: String,
    )
}