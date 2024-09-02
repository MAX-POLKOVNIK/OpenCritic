package com.opencritic.games.details.ui

data class ScreenshotItem(
    val thumbnailUrl: String,
    private val onClick: (ScreenshotItem) -> Unit,
) : MediaItem() {
    fun click() = onClick(this)
}