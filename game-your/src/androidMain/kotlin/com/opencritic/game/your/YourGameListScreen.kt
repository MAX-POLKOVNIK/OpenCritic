package com.opencritic.game.your

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.opencritic.game.your.ui.YourGameListViewModel
import com.opencritic.mvvm.CommonScreen
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel

@Composable
fun YourGameListScreen(
    navController: NavController,
    viewModel: YourGameListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    CommonScreen(state = state) { content, modifier ->
        YourGameListState(state = content, modifier = modifier)
    }
}