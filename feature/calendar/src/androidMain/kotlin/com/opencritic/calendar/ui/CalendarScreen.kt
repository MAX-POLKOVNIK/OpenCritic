package com.opencritic.calendar.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CalendarScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = koinViewModel(),
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
    ) { content ->
        CalendarContent(content = content)
    }
}
