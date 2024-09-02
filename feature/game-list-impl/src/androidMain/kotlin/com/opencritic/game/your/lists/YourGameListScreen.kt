package com.opencritic.game.your.lists

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.game.your.ui.lists.YourGameListViewModel
import com.opencritic.mvvm.AppBarTitleMode
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun YourGameListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: YourGameListViewModel = koinViewModel()
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
        appBarTitleMode = AppBarTitleMode.Center
    ) {
        YourGameListState(state = it)
    }
}