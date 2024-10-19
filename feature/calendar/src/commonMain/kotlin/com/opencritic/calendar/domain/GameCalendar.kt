package com.opencritic.calendar.domain

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

data class GameCalendar(
    val months: List<GameMonth>
)

fun GameCalendar(
    start: LocalDate,
    end: LocalDate,
    games: List<CalendarGame>
): GameCalendar {
    val dateRange = start..end

    val yearsInCalendar = start.year..end.year

    val firstDatesOfMonths = buildList {
        yearsInCalendar.forEach { year ->
            Month.entries.forEach { month ->
                val date = LocalDate(year, month, 1)

                if (date in dateRange) {
                    add(date)
                }
            }
        }
    }

    val monthsAndYears = firstDatesOfMonths.map { date ->
        date.month to date.year
    }

    val gamesByMonths = monthsAndYears.map { (month, year) ->
        (month to year) to games.filter { it.releaseDate.month == month && it.releaseDate.year == year }
    }

    return GameCalendar(
        months = gamesByMonths.map { entry: Pair<Pair<Month, Int>, List<CalendarGame>> ->
            GameMonth(
                games = entry.second,
                year = entry.first.second,
                month = entry.first.first,
            )
        }
    )
}