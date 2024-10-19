package com.opencritic.calendar.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.opencritic.calendar.api.CalendarRoute
import com.opencritic.navigation.Route
import com.opencritic.navigation.ScreenCreator

data object CalendarScreenCreator : ScreenCreator<CalendarRoute.InitArgs>() {
    override val route: Route<CalendarRoute.InitArgs> = CalendarRoute

    @Composable
    override fun Composable(args: CalendarRoute.InitArgs, navController: NavController) {
        CalendarScreen(navController)
    }
}