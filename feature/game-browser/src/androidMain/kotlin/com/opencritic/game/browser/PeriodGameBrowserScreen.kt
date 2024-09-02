package com.opencritic.game.browser

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.game.browser.api.PeriodGameBrowserRoute
import com.opencritic.game.browser.ui.period.PeriodGameBrowserViewModel
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PeriodGameBrowserScreen(
    args: PeriodGameBrowserRoute.InitArgs,
    navController: NavController,
    viewModel: PeriodGameBrowserViewModel = koinViewModel { parametersOf(args) }
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        PeriodGameBrowserContent(content)
    }
}