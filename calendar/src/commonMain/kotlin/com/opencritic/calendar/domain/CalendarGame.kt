package com.opencritic.calendar.domain

import kotlinx.datetime.LocalDate

data class CalendarGame(
    val id: Long,
    val name: String,
    val posterImageUrl: String?,
    val releaseDate: LocalDate,
)