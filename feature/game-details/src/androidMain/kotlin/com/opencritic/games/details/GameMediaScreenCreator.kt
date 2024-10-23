package com.opencritic.games.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.api.ui.GameMediaRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object GameMediaScreenCreator : ScreenCreator<GameMediaRoute.InitArgs>() {
    override val route: Route<GameMediaRoute.InitArgs> = GameMediaRoute

    @Composable
    override fun Composable(args: GameMediaRoute.InitArgs, navController: NavController) {
        GameMediaScreen(args, navController)
    }
}