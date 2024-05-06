package com.opencritic.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.opencritic.main.ui.MainViewModel
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    viewModel.setRouter(navController.router())

    MainState(state, navController)
}