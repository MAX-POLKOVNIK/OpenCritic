package com.opencritic.calendar.domain

data class GameDay(
    val dayNumber: Int,
    val games: List<CalendarGame>,
)