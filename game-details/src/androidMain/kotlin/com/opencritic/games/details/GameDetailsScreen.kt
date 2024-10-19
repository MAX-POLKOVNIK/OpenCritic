package com.opencritic.games.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.ui.GameDetailsViewModel
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GameDetailsScreen(
    gameId: Long,
    gameName: String,
    navController: NavController,
    viewModel: GameDetailsViewModel = koinViewModel { parametersOf(gameId, gameName) },
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        GameDetailsContent(content)
    }
}