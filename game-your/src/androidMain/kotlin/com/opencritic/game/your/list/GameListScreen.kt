package com.opencritic.game.your.list

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.game.your.ui.list.GameListViewModel
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GameListScreen(
    gameListId: String,
    gameListName: String,
    navController: NavController,
    viewModel: GameListViewModel = koinViewModel { parametersOf(gameListId, gameListName) }
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        GameListContent(content)
    }
}