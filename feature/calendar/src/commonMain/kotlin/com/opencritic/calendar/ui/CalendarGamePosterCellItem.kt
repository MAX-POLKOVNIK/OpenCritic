package com.opencritic.calendar.ui

data class CalendarGamePosterCellItem(
    val id: Long,
    val posterImageUrl: String?,
    val onClick: () -> Unit,
)

@Suppress("FunctionName")
fun CalendarGamePosterCellItem_PreviewData(): CalendarGamePosterCellItem =
    CalendarGamePosterCellItem(
        id = 11,
        posterImageUrl = "https://img.opencritic.com/game/16970/PoUwlx4E.jpg",
        onClick = {}
    )