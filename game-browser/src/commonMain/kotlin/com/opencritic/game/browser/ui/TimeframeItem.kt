package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.resources.text.TextSource

data class TimeframeItem(
    val key: GameTimeframe,
    val text: TextSource,
)