package com.opencritic.game.browser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.opencritic.game.browser.ui.GameBrowserState
import com.opencritic.game.browser.ui.GameBrowserViewModel
import com.opencritic.mvvm.ErrorBox
import com.opencritic.mvvm.LoadingBox
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameBrowserScreen(
    navController: NavController,
    viewModel: GameBrowserViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    when (val s = state) {
        is GameBrowserState.Content -> GameBrowserStateContent(s)
        is GameBrowserState.Error -> ErrorBox(s)
        is GameBrowserState.Loading -> LoadingBox(s)
    }
}