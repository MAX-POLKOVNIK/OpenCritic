package com.opencritic.resources

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource

actual sealed interface IconResource {
    data class BuiltInIconResource(val icon: ImageVector) : IconResource
    data class CustomIconResource(@DrawableRes val icon: Int) : IconResource
}

@Composable
fun IconResource.asPainter(): Painter =
    when (this) {
        is IconResource.BuiltInIconResource -> rememberVectorPainter(image = icon)
        is IconResource.CustomIconResource -> painterResource(id = icon)
    }

internal fun ImageVector.iconRes(): IconResource =
    IconResource.BuiltInIconResource(this)

internal fun Int.iconRes(): IconResource =
    IconResource.CustomIconResource(this)