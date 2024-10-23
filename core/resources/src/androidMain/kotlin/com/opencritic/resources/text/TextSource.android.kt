package com.opencritic.resources.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun TextSource.text(): String =
    when (this) {
        is StringTextSource -> this.text()
        is ResourceTextSource -> this.text(LocalContext.current)
        is ResourceFormattedTextSource -> this.text(LocalContext.current)
    }

@Composable
fun TextSource?.textOrEmpty(): String =
    this?.text() ?: ""