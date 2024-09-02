package com.opencritic.game.your.list

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.game.your.ui.list.GameListViewModel
import com.opencritic.games.list.api.GameListRoute
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GameListScreen(
    args: GameListRoute.InitArgs,
    navController: NavController,
    viewModel: GameListViewModel = koinViewModel { parametersOf(args) }
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        GameListContent(content)
    }
}