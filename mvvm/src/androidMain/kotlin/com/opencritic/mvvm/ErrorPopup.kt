package com.opencritic.mvvm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.largePadding
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorPopup(
    state: BaseErrorState,
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(largePadding)
            ) {
                Icon(
                    painter = Icons.warning.asPainter(),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .size(56.dp)
                )
                Text(
                    text = state.message.text(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(defaultPadding)

                )

                if (state.actionText != null) {
                    Button(
                        onClick = { state.action?.invoke() }
                    ) {
                        Text(text = state.actionText?.text() ?: "DO SOMETHING!")
                    }
                }
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(),
    ) {

    }
}

@Composable
@Preview
fun ErrorPopup_Preview() {
    ErrorPopup(state = object : BaseErrorState("Some kind of long error: Connected to the target VM, address: 'localhost:54736', transport: 'socket'".asTextSource()) {})
}