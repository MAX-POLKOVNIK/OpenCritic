package com.opencritic.games.details.reviews.game

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.api.ui.GameReviewsRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object GameReviewsScreenCreator : ScreenCreator<GameReviewsRoute.InitArgs>() {
    override val route: Route<GameReviewsRoute.InitArgs> = GameReviewsRoute

    @Composable
    override fun Composable(args: GameReviewsRoute.InitArgs, navController: NavController) {
        GameReviewsScreen(args, navController)
    }
}