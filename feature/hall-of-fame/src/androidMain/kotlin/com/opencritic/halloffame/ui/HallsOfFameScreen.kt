package com.opencritic.halloffame.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HallsOfFameScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HallsOfFameViewModel = koinViewModel()
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
    ) { content ->
        HallsOfFameContent(content = content)
    }
}