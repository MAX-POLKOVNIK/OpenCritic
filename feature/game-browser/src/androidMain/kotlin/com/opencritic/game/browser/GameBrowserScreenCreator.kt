package com.opencritic.game.browser

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.game.browser.api.GameBrowserRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object GameBrowserScreenCreator : ScreenCreator<GameBrowserRoute.InitArgs>() {
    override val route: Route<GameBrowserRoute.InitArgs> = GameBrowserRoute

    @Composable
    override fun Composable(args: GameBrowserRoute.InitArgs, navController: NavController) {
        GameBrowserScreen(navController)
    }
}