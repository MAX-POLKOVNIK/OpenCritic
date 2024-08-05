package com.opencritic.game.browser

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.game.browser.ui.GameBrowserViewModel
import com.opencritic.mvvm.AppBarTitleMode
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameBrowserScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GameBrowserViewModel = koinViewModel()
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
        appBarTitleMode = AppBarTitleMode.Center,
    ) { content ->
        GameBrowserStateContent(content)
    }
}