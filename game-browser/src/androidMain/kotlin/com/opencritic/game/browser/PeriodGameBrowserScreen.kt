package com.opencritic.game.browser

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.game.browser.ui.period.PeriodGameBrowserViewModel
import com.opencritic.mvvm.CommonScaffoldScreen
import com.opencritic.navigation.PeriodGameBrowserDestination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PeriodGameBrowserScreen(
    period: PeriodGameBrowserDestination.Period,
    navController: NavController,
    viewModel: PeriodGameBrowserViewModel = koinViewModel { parametersOf(period) }
) {
    CommonScaffoldScreen(viewModel = viewModel, navController = navController) { content ->
        PeriodGameBrowserContent(content)
    }
}