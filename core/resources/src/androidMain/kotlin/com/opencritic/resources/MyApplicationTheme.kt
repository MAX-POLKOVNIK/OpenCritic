package com.opencritic.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            secondary = Color(0xFF03DAC5),
            tertiary = Color(0xFF3700B3)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC5),
            tertiary = Color(0xFF3700B3)
        )
    }

    val helveticaNeue = FontFamily(
        Font(R.font.helvetica_neue_black,               style = FontStyle.Normal, weight = FontWeight.Black),
        Font(R.font.helvetica_neue_black_italic,        style = FontStyle.Italic, weight = FontWeight.Black),
        Font(R.font.helvetica_neue_bold,                style = FontStyle.Normal, weight = FontWeight.Bold),
        Font(R.font.helvetica_neue_bold_italic,         style = FontStyle.Italic, weight = FontWeight.Bold),
        Font(R.font.helvetica_neue_heavy,               style = FontStyle.Normal, weight = FontWeight.ExtraBold),
        Font(R.font.helvetica_neue_heavy_italic,        style = FontStyle.Italic, weight = FontWeight.ExtraBold),
        Font(R.font.helvetica_neue_roman,               style = FontStyle.Normal, weight = FontWeight.Normal),
        Font(R.font.helvetica_neue_italic,              style = FontStyle.Italic, weight = FontWeight.Normal),
        Font(R.font.helvetica_neue_light,               style = FontStyle.Normal, weight = FontWeight.Light),
        Font(R.font.helvetica_neue_light_italic,        style = FontStyle.Italic, weight = FontWeight.Light),
        Font(R.font.helvetica_neue_medium,              style = FontStyle.Normal, weight = FontWeight.Medium),
        Font(R.font.helvetica_neue_medium_italic,       style = FontStyle.Italic, weight = FontWeight.Medium),
        Font(R.font.helvetica_neue_thin,                style = FontStyle.Normal, weight = FontWeight.ExtraLight),
        Font(R.font.helvetica_neue_thin_italic,         style = FontStyle.Italic, weight = FontWeight.ExtraLight),
        Font(R.font.helvetica_neue_ultra_light,         style = FontStyle.Normal, weight = FontWeight.Thin),
        Font(R.font.helvetica_neue_ultra_light_italic,  style = FontStyle.Italic, weight = FontWeight.Thin),
    )

    val defaultTypography = Typography()

    val typography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),

        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),

        titleLarge = defaultTypography.titleLarge.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),

        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),

        labelLarge = defaultTypography.labelLarge.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = helveticaNeue, letterSpacing = 0.sp),
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
