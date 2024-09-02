package com.opencritic.games.details.api.ui

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object GameMediaRoute : Route<GameMediaRoute.InitArgs> by route(screenName = "game-media") {
    @Serializable
    data class InitArgs(
        val gameId: Long,
        val gameName: String,
    )
}