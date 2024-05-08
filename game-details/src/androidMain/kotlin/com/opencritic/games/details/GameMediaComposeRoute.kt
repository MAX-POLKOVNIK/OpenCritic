package com.opencritic.games.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.navigation.GameMediaDestination

fun NavGraphBuilder.gameMediaComposeRoute(navController: NavController) {
    composable(
        route = GameMediaDestination.path,
        arguments = listOf(
            navArgument(GameMediaDestination.GAME_ID_KEY) { type = NavType.LongType },
            navArgument(GameMediaDestination.GAME_NAME_KEY) { type = NavType.StringType }
        )
    ) {
        val gameId = requireNotNull(it.arguments?.getLong(GameMediaDestination.GAME_ID_KEY))
        val gameName = requireNotNull(it.arguments?.getString(GameMediaDestination.GAME_NAME_KEY))

        GameMediaScreen(gameId, gameName, navController)
    }
}