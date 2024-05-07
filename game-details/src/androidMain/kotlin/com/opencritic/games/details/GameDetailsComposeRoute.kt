package com.opencritic.games.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.navigation.GameDetailsDestination

fun NavGraphBuilder.gameDetailsRoute(navController: NavController) {
    composable(
        route = GameDetailsDestination.path,
        arguments = listOf(
            navArgument(GameDetailsDestination.GAME_ID_KEY) { type = NavType.LongType },
            navArgument(GameDetailsDestination.GAME_NAME_KEY) { type = NavType.StringType }
        )
    ) {
        val gameId = requireNotNull(it.arguments?.getLong(GameDetailsDestination.GAME_ID_KEY))
        val gameName = requireNotNull(it.arguments?.getString(GameDetailsDestination.GAME_NAME_KEY))

        GameDetailsScreen(gameId, gameName, navController)
    }
}