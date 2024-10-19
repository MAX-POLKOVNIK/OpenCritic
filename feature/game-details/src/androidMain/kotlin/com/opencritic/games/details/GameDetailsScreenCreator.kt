package com.opencritic.games.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object GameDetailsScreenCreator : ScreenCreator<GameDetailsRoute.InitArgs>() {
    override val route: Route<GameDetailsRoute.InitArgs> = GameDetailsRoute

    @Composable
    override fun Composable(args: GameDetailsRoute.InitArgs, navController: NavController) {
        GameDetailsScreen(args, navController)
    }
}