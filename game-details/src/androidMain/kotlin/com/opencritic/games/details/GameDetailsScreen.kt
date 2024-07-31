package com.opencritic.games.details

import androidx.compose.foundation.layout.padding
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
import com.opencritic.games.details.ui.GameDetailsState
import com.opencritic.games.details.ui.GameDetailsViewModel
import com.opencritic.mvvm.ErrorBox
import com.opencritic.mvvm.LoadingBox
import com.opencritic.navigation.router
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    gameId: Long,
    gameName: String,
    navController: NavController,
    viewModel: GameDetailsViewModel = koinViewModel { parametersOf(gameId, gameName) },
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
                            painter = Icons.arrowBack.asPainter(),
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ) { paddings ->
        when (val s = state) {
            is GameDetailsState.Content-> GameDetailsStateContent(s, Modifier.padding(paddings))
            is GameDetailsState.Loading -> LoadingBox(s, Modifier.padding(paddings))
            is GameDetailsState.Error -> ErrorBox(s, Modifier.padding(paddings))
        }
    }
}