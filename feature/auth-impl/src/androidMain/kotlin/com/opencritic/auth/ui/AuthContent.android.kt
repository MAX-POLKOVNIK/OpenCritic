package com.opencritic.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.text.text

@Composable
fun AuthContent(
    content: AuthContent,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf(content.token) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = content.descriptionText.text(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = defaultPadding)
                .padding(horizontal = defaultPadding)
        )
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                content.onTokenChanged(it)
            },
            label = { Text(content.tokenHint.text()) },
            modifier = Modifier
                .padding(defaultPadding)
                .fillMaxWidth()
        )

        Button(
            onClick = content.onAuthButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding)
        ) {
            Text(text = content.authButtonText.text())
        }

        Button(
            onClick = content.onUseOfflineListsClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = defaultPadding)
        ) {
            Text(text = content.useOfflineListsText.text())
        }
    }
}

@Preview
@Composable
fun AuthContent_Preview() {
    AuthContent(
        content = AuthContent_PreviewData()
    )
}