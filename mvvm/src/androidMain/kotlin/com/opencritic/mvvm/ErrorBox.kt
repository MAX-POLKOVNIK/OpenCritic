package com.opencritic.mvvm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.text

@Composable
fun ErrorBox(
    state: BaseErrorState,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
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

                Button(
                    onClick = { state.action() }
                ) {
                    Text(text = state.actionText.text())
                }
            }
        }
    }
}

@Composable
@Preview
fun ErrorBox_Preview() {
    ErrorBox(
        state = ErrorState(
            "Some kind of long error: Connected to the target VM, address: 'localhost:54736', transport: 'socket'".asTextSource()
        ) {}
    )
}