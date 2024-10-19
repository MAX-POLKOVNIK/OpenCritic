package com.opencritic.api.dto.calendar

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CalendarDto(
    val games: List<CalendarGameDto>,
    val startDate: Instant,
    val endDate: Instant,
    val title: String,
)