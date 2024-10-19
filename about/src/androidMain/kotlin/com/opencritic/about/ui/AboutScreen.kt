package com.opencritic.about.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AboutScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AboutViewModel = koinViewModel()
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
    ) { content ->
        AboutContent(content = content)
    }
}