package com.opencritic.game.your.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.opencritic.auth.ui.AuthScreen
import com.opencritic.navigation.AuthDestination
import com.opencritic.navigation.GameDetailsDestination
import com.opencritic.navigation.GameListDestination

fun NavGraphBuilder.gameListRoute(navController: NavController) {
    composable(
        route = GameListDestination.path,
        arguments = listOf(
            navArgument(GameListDestination.GAME_LIST_ID_KEY) { type = NavType.StringType },
            navArgument(GameListDestination.GAME_LIST_NAME_KEY) { type = NavType.StringType }
        )
    ) {
        val gameListId = requireNotNull(it.arguments?.getString(GameListDestination.GAME_LIST_ID_KEY))
        val gameListName = requireNotNull(it.arguments?.getString(GameListDestination.GAME_LIST_NAME_KEY))

        GameListScreen(gameListId, gameListName, navController)
    }
}