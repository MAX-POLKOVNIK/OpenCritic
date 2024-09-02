package com.opencritic.calendar.domain

interface CalendarRepository {
    suspend fun getCalendar(): GameCalendar
}