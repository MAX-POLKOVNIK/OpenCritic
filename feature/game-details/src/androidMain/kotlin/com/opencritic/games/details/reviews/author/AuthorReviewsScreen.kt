package com.opencritic.games.details.reviews.author

import androidx.compose.foundation.layout.padding
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
import com.opencritic.games.details.api.ui.AuthorReviewsRoute
import com.opencritic.games.details.ui.AuthorReviewsState
import com.opencritic.games.details.ui.AuthorReviewsViewModel
import com.opencritic.mvvm.ErrorBox
import com.opencritic.mvvm.LoadingBox
import com.opencritic.navigation.router
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorReviewsScreen(
    args: AuthorReviewsRoute.InitArgs,
    navController: NavController,
    viewModel: AuthorReviewsViewModel = koinViewModel { parametersOf(args) }
) {
    val state by viewModel.state.collectAsState()
    val router = navController.router()

    viewModel.setRouter(router)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                ),
                title = {
                    Text(
                        text = state.titleText.text(),
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
            is AuthorReviewsState.Content-> AuthorReviewsStateContent(s, Modifier.padding(paddings))
            is AuthorReviewsState.Loading -> LoadingBox(s, Modifier.padding(paddings))
            is AuthorReviewsState.Error -> ErrorBox(s, Modifier.padding(paddings))
        }
    }
}