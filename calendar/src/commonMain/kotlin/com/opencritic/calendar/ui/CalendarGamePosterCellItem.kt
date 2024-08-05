package com.opencritic.calendar.ui

data class CalendarGamePosterCellItem(
    val id: Long,
    val posterImageUrl: String?,
    val onClick: () -> Unit,
)