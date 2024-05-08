package com.opencritic.resources

import platform.UIKit.UIColor

fun Color.toUIColor(): UIColor =
    UIColor(
        red = ((argb shr 16) and 0xFF).toDouble() / 255,
        green = ((argb shr 8) and 0xFF).toDouble() / 255,
        blue = (argb and 0xFF).toDouble() / 255,
        alpha = ((argb shr 24) and 0xFF).toDouble() / 255,
    )