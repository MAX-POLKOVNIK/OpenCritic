package com.opencritic.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.opencritic.navigation.router
import com.opencritic.search.ui.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    SearchState(state)
}