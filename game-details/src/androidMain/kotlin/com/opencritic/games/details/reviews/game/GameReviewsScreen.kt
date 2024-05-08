package com.opencritic.games.details.reviews.game

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.opencritic.games.details.ui.GameReviewsState
import com.opencritic.games.details.ui.GameReviewsViewModel
import com.opencritic.mvvm.ErrorBox
import com.opencritic.mvvm.LoadingBox
import com.opencritic.navigation.router
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameReviewsScreen(
    gameId: Long,
    gameName: String,
    navController: NavController,
    viewModel: GameReviewsViewModel = koinViewModel { parametersOf(gameId, gameName) },
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = state.titleText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { router.navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ) { paddings ->
        when (val s = state) {
            is GameReviewsState.Content-> GameReviewsStateContent(s, Modifier.padding(paddings))
            is GameReviewsState.Loading -> LoadingBox(s, Modifier.padding(paddings))
            is GameReviewsState.Error -> ErrorBox(s, Modifier.padding(paddings))
        }
    }
}