package com.opencritic.calendar.domain

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.plus

data class GameMonth(
    val name: String,
    val weeks: List<GameWeek>,
    val year: Int,
)

fun GameMonth(
    month: Month,
    year: Int,
    games: List<CalendarGame>
): GameMonth {
    val startDate = LocalDate(year, month, 1)

    val endDate = startDate
        .plus(DatePeriod(months = 1))
        .minus(DatePeriod(days = 1))

    val dateRange = startDate..endDate

    val daysOfWeek = dayOfWeekOrdered()

    val monthWeeks = mutableListOf<GameWeek>()

    var currentDate = startDate

    while (currentDate in dateRange) {
        val missedDays = daysOfWeek.indexOf(currentDate.dayOfWeek)
        val startWeekDate = currentDate.minus(DatePeriod(days = missedDays))
        val endOfWeekDate = currentDate.plus(DatePeriod(days = 7 - missedDays - 1))

        monthWeeks.add(
            GameWeek(
                range = startWeekDate..endOfWeekDate,
                month = month,
                year = year,
                games = games
            )
        )

        currentDate = currentDate.plus(DatePeriod(days = 7))
    }

    return GameMonth(
        name = month.name,
        year = year,
        weeks = monthWeeks,
    )
}