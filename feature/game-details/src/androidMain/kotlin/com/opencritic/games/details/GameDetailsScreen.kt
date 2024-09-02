package com.opencritic.games.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.details.ui.GameDetailsViewModel
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GameDetailsScreen(
    args: GameDetailsRoute.InitArgs,
    navController: NavController,
    viewModel: GameDetailsViewModel = koinViewModel { parametersOf(args) },
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        GameDetailsContent(content)
    }
}