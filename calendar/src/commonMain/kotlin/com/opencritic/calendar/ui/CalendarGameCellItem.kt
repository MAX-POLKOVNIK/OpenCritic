package com.opencritic.calendar.ui


data class CalendarGameCellItem(
    val dayText: String,
    val isBackgroundVisible: Boolean,
    val posters: List<CalendarGamePosterCellItem>,
    val dotsVisible: Boolean = posters.size > 1,
    val currentPosterIndex: Int? = 1.takeIf { posters.isNotEmpty() },
    val dotsCount: Int = posters.size,
) {
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