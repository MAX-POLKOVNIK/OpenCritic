package com.opencritic.game.your.list

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.list.api.GameListRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object GameListScreenCreator : ScreenCreator<GameListRoute.InitArgs>() {
    override val route: Route<GameListRoute.InitArgs> = GameListRoute

    @Composable
    override fun Composable(args: GameListRoute.InitArgs, navController: NavController) {
        GameListScreen(args, navController)
    }
}