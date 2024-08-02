package com.opencritic.mvvm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.text

@OptIn(ExperimentalMaterial3Api::class)
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