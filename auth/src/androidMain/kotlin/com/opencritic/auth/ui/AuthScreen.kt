package com.opencritic.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.opencritic.mvvm.CommonScaffoldScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel(),
) {
    CommonScaffoldScreen(
        viewModel = viewModel,
        navController = navController,
        modifier = modifier
    ) { content ->
        AuthContent(content)
    }
}