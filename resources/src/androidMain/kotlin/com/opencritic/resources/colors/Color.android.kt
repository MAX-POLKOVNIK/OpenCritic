package com.opencritic.resources.colors


fun Color.toCompose(): androidx.compose.ui.graphics.Color =
    androidx.compose.ui.graphics.Color(argb)