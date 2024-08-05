package com.opencritic.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.dashboard.ui.DashboardViewModel
import com.opencritic.mvvm.AppBarTitleMode
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = koinViewModel(),
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
        appBarTitleMode = AppBarTitleMode.Center,
    ) { content ->
        DashboardStateContent(state = content)
    }
}