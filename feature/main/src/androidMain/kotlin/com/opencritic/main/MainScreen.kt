package com.opencritic.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.main.ui.MainViewModel
import com.opencritic.mvvm.AppBarTitleMode
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel()
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
        appBarTitleMode = AppBarTitleMode.None,
    ) { content ->
        MainState(content, navController)
    }
}