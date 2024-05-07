package com.opencritic.resources


fun Color.toCompose(): androidx.compose.ui.graphics.Color =
    androidx.compose.ui.graphics.Color(argb)