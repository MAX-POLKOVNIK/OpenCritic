package com.opencritic.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.opencritic.dashboard.ui.DashboardState
import com.opencritic.dashboard.ui.DashboardViewModel
import com.opencritic.mvvm.ErrorBox
import com.opencritic.mvvm.LoadingBox
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    when (val s = state) {
        is DashboardState.Content -> DashboardStateContent(s)
        is DashboardState.Error -> ErrorBox(s)
        is DashboardState.Loading -> LoadingBox(s)
    }
}