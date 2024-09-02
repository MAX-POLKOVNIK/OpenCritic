package com.opencritic.game.browser

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.game.browser.api.PeriodGameBrowserRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object PeriodGameBrowserScreenCreator : ScreenCreator<PeriodGameBrowserRoute.InitArgs>() {
    override val route: Route<PeriodGameBrowserRoute.InitArgs> = PeriodGameBrowserRoute

    @Composable
    override fun Composable(args: PeriodGameBrowserRoute.InitArgs, navController: NavController) {
        PeriodGameBrowserScreen(args, navController)
    }
}