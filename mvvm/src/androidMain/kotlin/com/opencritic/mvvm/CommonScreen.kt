package com.opencritic.mvvm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <Content> CommonScreen(
    state: CommonViewModelState<Content>,
    modifier: Modifier = Modifier,
    content: @Composable (Content, Modifier) -> Unit,
) {
    if (state.fullScreenError != null) {
        ErrorBox(
            state = state.fullScreenError,
            modifier = modifier,
        )
    }

    if (state.fullScreenLoading != null) {
        LoadingBox(
            state = state.fullScreenLoading,
            modifier = modifier,
        )
    }

    if (state.content != null) {
        content(state.content, modifier)
    }
}