package com.opencritic.game.browser.api

import com.opencritic.navigation.Route
import com.opencritic.navigation.route
import kotlinx.serialization.Serializable

data object GameBrowserRoute : Route<GameBrowserRoute.InitArgs> by route("game-browser") {
    @Serializable
    data object InitArgs
}