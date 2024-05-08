package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.GameSorting

data class GameSortItem(
    val key: GameSorting,
    val text: String,
)