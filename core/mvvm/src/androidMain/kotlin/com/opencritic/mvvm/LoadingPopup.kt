package com.opencritic.mvvm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.largePadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingPopup(
    state: BaseLoadingState,
    modifier: Modifier = Modifier,
) {
    BasicAlertDialog(
        onDismissRequest = {}
    ) {
        Surface(
            modifier = Modifier
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(largePadding)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                )
                Text(
                    text = state.text,
                    modifier = Modifier
                        .padding(start = defaultPadding)
                )
            }
        }
    }
}

@Composable
@Preview
fun LoadingPopup_Preview() {
    LoadingPopup(
        state = object : BaseLoadingState("Loading...") {}
    )
}