package com.opencritic.about.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.about.api.ui.AboutRoute
import com.opencritic.mvvm.CommonScaffoldScreen
import com.opencritic.mvvm.viewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AboutScreen(
    args: AboutRoute.InitArgs,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AboutViewModel = viewModel(args)
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
    ) { content ->
        AboutContent(content = content)
    }
}