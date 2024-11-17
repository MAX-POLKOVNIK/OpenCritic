package com.opencritic.calendar.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList


data class CalendarGameCellItem(
    private val id: Int,
    private val posters: ImmutableList<CalendarGamePosterCellItem>,
    val dayText: String,
    val isBackgroundVisible: Boolean,
    val isDotsVisible: Boolean = posters.size > 1,
    val currentPosterIndex: Int? = 0.takeIf { posters.isNotEmpty() },
    val dotsCount: Int = posters.size,
) {
    val currentPoster: CalendarGamePosterCellItem? =
        currentPosterIndex?.let { posters[it] }

    fun switchedNext(): CalendarGameCellItem =
        copy(
            currentPosterIndex = if (currentPosterIndex == null) {
                null
            } else {
                if (currentPosterIndex + 1 < posters.size) currentPosterIndex + 1
                else 0
            }
        )
}

@Suppress("FunctionName")
fun CalendarGameCellItem_PreviewData(): CalendarGameCellItem =
    CalendarGameCellItem(
        dayText = "31",
        isBackgroundVisible = true,
        id = 31,
        posters = listOf(
            CalendarGamePosterCellItem_PreviewData(),
            CalendarGamePosterCellItem_PreviewData().copy(
                id = 22,
                posterImageUrl = "https://img.opencritic.com/game/16994/KHFoWQfz.jpg"
            ),
        ).toImmutableList()
    )