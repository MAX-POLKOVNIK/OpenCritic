package com.opencritic.mvvm

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.opencritic.navigation.Router
import com.opencritic.navigation.router
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text

@Composable
fun <Content : ScreenContent> CommonScaffoldScreen(
    viewModel: BaseContentViewModel<Content>,
    navController: NavController,
    modifier: Modifier = Modifier,
    appBarTitleMode: AppBarTitleMode = AppBarTitleMode.Start,
    content: @Composable (Content) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    Scaffold(
        contentWindowInsets = WindowInsets(top = 0),
        topBar = {
            when (appBarTitleMode) {
                AppBarTitleMode.Start -> StartAppBar(state, router)
                AppBarTitleMode.Center -> CenterAppBar(state)
                AppBarTitleMode.None -> {}
            }
        },
        modifier = modifier
    ) { paddings ->
        CommonScreen(
            state = state,
            modifier = Modifier.padding(paddings)
        ) { content ->
            content(content)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun <Content : ScreenContent> StartAppBar(
    state: CommonViewModelState<Content>,
    router: Router,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
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
            val content = state.content as? ActionedScreenContent
            if (content != null && content.isActionVisible) {
                IconButton(onClick = { state.content.onAction() }) {
                    Icon(content.actionIconResource.asPainter(), contentDescription = null)
                }
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun <Content : ScreenContent> CenterAppBar(
    state: CommonViewModelState<Content>,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
        ),
        title = {
            Text(
                text = state.title?.text() ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            val content = state.content as? ActionedScreenContent
            if (content != null && content.isActionVisible) {
                IconButton(onClick = { state.content.onAction() }) {
                    Icon(content.actionIconResource.asPainter(), contentDescription = null)
                }
            }
        },
        modifier = modifier,
    )
}