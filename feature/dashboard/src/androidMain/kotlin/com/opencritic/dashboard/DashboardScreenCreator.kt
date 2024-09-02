package com.opencritic.dashboard

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.dashboard.api.DashboardRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object DashboardScreenCreator : ScreenCreator<DashboardRoute.InitArgs>() {
    override val route: Route<DashboardRoute.InitArgs> = DashboardRoute

    @Composable
    override fun Composable(args: DashboardRoute.InitArgs, navController: NavController) {
        DashboardScreen(navController)
    }
}