package com.opencritic.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
fun SharedImageResource.asPainter(): Painter =
    painterResource(id = imageRes.drawableResId)