package com.opencritic.game.your.lists

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.list.api.GameListsRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object GameListsScreenCreator : ScreenCreator<GameListsRoute.InitArgs>() {
    override val route: Route<GameListsRoute.InitArgs> = GameListsRoute

    @Composable
    override fun Composable(args: GameListsRoute.InitArgs, navController: NavController) {
        YourGameListScreen(navController)
    }
}