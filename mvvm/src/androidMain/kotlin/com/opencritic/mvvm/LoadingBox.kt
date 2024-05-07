package com.opencritic.mvvm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.resources.defaultPadding

@Composable
fun LoadingBox(
    state: BaseLoadingState,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(56.dp)
            )
            Text(
                text = state.text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(top = defaultPadding)
            )
        }
    }
}

@Composable
@Preview
fun LoadingBox_Preview() {
    LoadingBox(
        state = object : BaseLoadingState("Loading...") {}
    )
}