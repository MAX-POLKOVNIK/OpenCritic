package com.opencritic.calendar.domain

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.plus

data class GameWeek(
    val days: List<GameDay?>
)

fun GameWeek(
    range: ClosedRange<LocalDate>,
    month: Month,
    year: Int,
    games: List<CalendarGame>
): GameWeek {
    val start = range.start

    val days = mutableListOf<GameDay?>()

    for (i in 0..<7) {
        val date = start.plus(DatePeriod(days = i))

        if (date.month == month && year == date.year) {
            days.add(
                GameDay(
                    dayNumber = date.dayOfMonth,
                    games = games.filter { it.releaseDate == date }
                )
            )
        } else {
            days.add(null)
        }
    }

    return GameWeek(
        days = days
    )
}
