package com.opencritic.resources.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.desc.desc

@Composable
fun TextSource.text(): String =
    when (this) {
        is DateTextSource -> this.text()
        is StringTextSource -> string
        is ResourceTextSource -> resId.desc().toString(LocalContext.current)
        is ResourceFormattedTextSource -> desc.toString(LocalContext.current)
    }
