package com.opencritic.games.list.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object GameListsRoute : Route<GameListsRoute.InitArgs> by route("game-lists") {
    @Serializable
    data object InitArgs
}