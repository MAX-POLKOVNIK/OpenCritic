package com.opencritic.calendar.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.calendar.CalendarDto
import com.opencritic.api.dto.calendar.CalendarGameDto
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.calendar.domain.CalendarGame
import com.opencritic.calendar.domain.CalendarRepository
import com.opencritic.calendar.domain.GameCalendar
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class CalendarRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CalendarRepository {
    override suspend fun getCalendar(): GameCalendar =
        withContext(defaultDispatcher) {
            GameCalendar(openCriticsApi.getCalendar())
        }

    private fun GameCalendar(dto: CalendarDto): GameCalendar =
        GameCalendar(
            start = dto.startDate.toLocalDateTime(TimeZone.UTC).date,
            end = dto.endDate.toLocalDateTime(TimeZone.UTC).date,
            games = dto.games.map { CalendarGame(it) }
        )

    private fun CalendarGame(dto: CalendarGameDto): CalendarGame =
        CalendarGame(
            id = dto.id,
            name = dto.name,
            posterImageUrl = dto.images.box?.sm?.prefixedImageUrl(),
            releaseDate = dto.firstReleaseDate.toLocalDateTime(TimeZone.UTC).date
        )

}