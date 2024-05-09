package com.opencritic.mvvm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.resources.defaultPadding

@Composable
fun ErrorBox(
    state: BaseErrorState,
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
            Icon(
                imageVector = Icons.Rounded.Warning,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .size(56.dp)
            )
            Text(
                text = state.message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(defaultPadding)

            )

            if (state.actionText != null) {
                Button(
                    onClick = { state.action?.invoke() }
                ) {
                    Text(text = "DO SOMETHING!")
                }
            }
        }
    }
}

@Composable
@Preview
fun ErrorBox_Preview() {
    ErrorBox(state = object : BaseErrorState("Some kind of long error: Connected to the target VM, address: 'localhost:54736', transport: 'socket'") {})
}