package com.opencritic.calendar.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.opencritic.navigation.CalendarDestination

fun NavGraphBuilder.calendarComposeRoute(navController: NavController) {
    composable(
        route = CalendarDestination.path,
        arguments = emptyList()
    ) {
        CalendarScreen(navController)
    }
}