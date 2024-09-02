package com.opencritic.mvvm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <Content : ScreenContent> CommonScreen(
    state: CommonViewModelState<Content>,
    modifier: Modifier = Modifier,
    content: @Composable (Content) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (state.content != null) {
            content(state.content)
        }

        if (state.fullScreenError != null) {
            ErrorBox(
                state = state.fullScreenError,
            )
        }

        if (state.fullScreenLoading != null) {
            LoadingBox(
                state = state.fullScreenLoading,
            )
        }

        if (state.loadingPopup != null) {
            LoadingPopup(state = state.loadingPopup)
        }

        if (state.errorPopup != null) {
            ErrorPopup(state = state.errorPopup)
        }

        SnackBar(
            message = state.toast?.text
        )
    }
}