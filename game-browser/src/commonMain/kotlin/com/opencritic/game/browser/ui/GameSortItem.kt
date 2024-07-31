package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.resources.text.TextSource

data class GameSortItem(
    val key: GameSorting,
    val text: TextSource,
)