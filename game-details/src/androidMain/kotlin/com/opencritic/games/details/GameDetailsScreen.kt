package com.opencritic.games.details

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.opencritic.games.details.ui.GameDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {

        }
    ) {

    }
}