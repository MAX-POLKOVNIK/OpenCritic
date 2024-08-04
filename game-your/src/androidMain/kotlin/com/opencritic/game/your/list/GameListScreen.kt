package com.opencritic.game.your.list

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
import com.opencritic.game.your.ui.list.GameListViewModel
import com.opencritic.mvvm.CommonScreen
import com.opencritic.navigation.router
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    gameListId: String,
    gameListName: String,
    navController: NavController,
    viewModel: GameListViewModel = koinViewModel { parametersOf(gameListId, gameListName) }
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
                        text = state.title?.text() ?: "",
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
                actions = {
                    val content = state.content
                    if (content != null && content.isShareVisible) {
                        IconButton(onClick = { state.content?.onShareClick?.invoke() }) {
                            Icon(content.shareIconResource.asPainter(), contentDescription = null)
                        }
                    }
                }
            )
        }
    ) { paddings ->
        CommonScreen(
            state = state,
            modifier = Modifier.padding(paddings)
        ) { content, modifier ->
            GameListContent(content = content, modifier = modifier)
        }
    }
}