package com.opencritic.mvvm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.text

@Composable
fun SnackBar(
    message: TextSource?,
    alignValue: Alignment = Alignment.BottomCenter
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val text = message?.text() ?: ""

    LaunchedEffect(key1 = text)
    {
        if (text.isNotBlank()) {
            snackbarHostState.showSnackbar(
                message = text,
                duration = SnackbarDuration.Indefinite,
            )
        } else {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    SnackbarHost(hostState = snackbarHostState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 32.dp)
                    .align(alignValue),
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.contentColorFor(
                        MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}