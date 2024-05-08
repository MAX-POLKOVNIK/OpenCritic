package com.opencritic.games.details.reviews.game

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.navigation.GameReviewsDestination

fun NavGraphBuilder.gameReviewsComposeRoute(navController: NavController) {
    composable(
        route = GameReviewsDestination.path,
        arguments = listOf(
            navArgument(GameReviewsDestination.GAME_ID_KEY) { type = NavType.LongType },
            navArgument(GameReviewsDestination.GAME_NAME_KEY) { type = NavType.StringType }
        )
    ) {
        val gameId = requireNotNull(it.arguments?.getLong(GameReviewsDestination.GAME_ID_KEY))
        val gameName = requireNotNull(it.arguments?.getString(GameReviewsDestination.GAME_NAME_KEY))

        GameReviewsScreen(gameId, gameName, navController)
    }
}