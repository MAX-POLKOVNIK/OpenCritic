package com.opencritic.halloffame.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.halloffame.api.HallOfFameRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object HallsOfFameScreenCreator : ScreenCreator<HallOfFameRoute.InitArgs>() {
    override val route: Route<HallOfFameRoute.InitArgs> = HallOfFameRoute

    @Composable
    override fun Composable(args: HallOfFameRoute.InitArgs, navController: NavController) {
        HallsOfFameScreen(navController)
    }
}