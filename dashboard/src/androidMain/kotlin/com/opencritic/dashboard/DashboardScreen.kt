package com.opencritic.dashboard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.opencritic.dashboard.ui.DashboardState
import com.opencritic.dashboard.ui.DashboardViewModel
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = koinViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    when (val s = state) {
        is DashboardState.Content -> DashboardStateContent(s)
        is DashboardState.Error -> Text("Error" + s.error)
        DashboardState.Loading -> Text("Loading...")
    }
}