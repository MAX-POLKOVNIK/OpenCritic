package com.opencritic.games.details.ui

data class ScreenshotItem(
    val thumbnailUrl: String,
    private val onClick: (ScreenshotItem) -> Unit = ::emptyClickHandler,
) : MediaItem() {
    fun click() = onClick(this)

    companion object {
        fun emptyClickHandler(item: ScreenshotItem) {}
    }
}