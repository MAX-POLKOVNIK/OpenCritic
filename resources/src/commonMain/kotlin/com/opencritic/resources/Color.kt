package com.opencritic.resources

data class Color(val argb: Int)

// color components in 0.0...1.0
fun Color(
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float = 1f,
): Color {
    require(red in 0.0..1.0)
    require(green in 0.0..1.0)
    require(blue in 0.0..1.0)
    require(alpha in 0.0..1.0)

    return Color(
        red = (red * 255).toInt(),
        green = (green * 255).toInt(),
        blue = (blue * 255).toInt(),
        alpha = alpha,
    )
}

// color components in 0 ... 255
// alpha in 0.0...1.0
fun Color(
    red: Int,
    green: Int,
    blue: Int,
    alpha: Float = 1f,
): Color {
    require(red in 0..255)
    require(green in 0..255)
    require(blue in 0..255)
    require(alpha in 0.0..1.0)

    return Color(
        argb = ((alpha * 255).toInt() shl 24) + (red shl 16) + (green shl 8) + blue
    )
}