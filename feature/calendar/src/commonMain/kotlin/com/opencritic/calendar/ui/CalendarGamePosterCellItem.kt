package com.opencritic.calendar.ui

data class CalendarGamePosterCellItem(
    val id: Long,
    val name: String,
    val posterImageUrl: String?,
    private val onClick: (CalendarGamePosterCellItem) -> Unit,
) {
    fun click() {
        onClick(this)
    }
}

@Suppress("FunctionName")
fun CalendarGamePosterCellItem_PreviewData(): CalendarGamePosterCellItem =
    CalendarGamePosterCellItem(
        id = 11,
        name = "Some name",
        posterImageUrl = "https://img.opencritic.com/game/16970/PoUwlx4E.jpg",
        onClick = {}
    )