package com.opencritic.game.browser

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.opencritic.game.browser.ui.GameBrowserContent
import com.opencritic.game.browser.ui.GameBrowserContent_PreviewData

@Composable
fun GameBrowserStateContent(
    state: GameBrowserContent,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
) {
    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        GameBrowserCompactContent(state = state, modifier = modifier)
    } else {
        GameBrowserRegularContent(state = state, modifier = modifier)
    }
}

@Preview
@Composable
fun GameBrowserStateContent_Preview() {
    GameBrowserStateContent(
        state = GameBrowserContent_PreviewData()
    )
}