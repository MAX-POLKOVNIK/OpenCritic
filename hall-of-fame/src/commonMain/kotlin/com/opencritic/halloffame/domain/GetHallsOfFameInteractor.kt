package com.opencritic.halloffame.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetHallsOfFameInteractor(
    private val repository: HallOfFameRepository,
) {
    suspend operator fun invoke(): Result<List<HallOfFameGameList>> =
        runCatching {
            coroutineScope {
                val currentYear = Clock.System.now()
                    .toLocalDateTime(timeZone = TimeZone.UTC)
                    .year

                val years = 2016..currentYear

                years.reversed()
                    .map { year ->
                        async { repository.getHallOfFame(year) }
                    }
                    .awaitAll()
            }
        }
}