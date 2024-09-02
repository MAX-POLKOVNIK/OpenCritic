package com.opencritic.games.list.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object GameListRoute : Route<GameListRoute.InitArgs> by route(screenName = "game-list") {
    @Serializable
    data class InitArgs(
        val listId: String,
        val listName: String,
    )
}