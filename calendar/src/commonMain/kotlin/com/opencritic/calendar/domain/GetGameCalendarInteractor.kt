package com.opencritic.calendar.domain

class GetGameCalendarInteractor(
    private val repository: CalendarRepository,
) {
    suspend operator fun invoke(): Result<GameCalendar> =
        runCatching { repository.getCalendar() }
}