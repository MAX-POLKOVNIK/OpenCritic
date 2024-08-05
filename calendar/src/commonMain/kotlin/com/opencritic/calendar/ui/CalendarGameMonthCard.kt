package com.opencritic.calendar.ui

import com.opencritic.calendar.domain.CalendarGame
import com.opencritic.calendar.domain.GameMonth

data class CalendarGameMonthCard(
    val nameText: String,
    val rows: List<CalendarGameRowItem>,
) {
    fun switchedNext(): CalendarGameMonthCard =
        copy(rows = rows.map { it.switchedNext() })
}

fun CalendarGameMonthCard(
    month: GameMonth,
    onPosterClick: (CalendarGame) -> Unit,
): CalendarGameMonthCard =
    CalendarGameMonthCard(
        nameText = month.name,
        rows = month.weeks.map { week ->
            CalendarGameRowItem(
                cells = week.days.map { day ->
                    CalendarGameCellItem(
                        dayText = day?.dayNumber?.toString() ?: "",
                        isBackgroundVisible = day != null,
                        posters = day?.games?.map { game ->
                            CalendarGamePosterCellItem(
                                id = game.id,
                                posterImageUrl = game.posterImageUrl,
                                onClick = { onPosterClick(game) }
                            )
                        } ?: emptyList()
                    )
                }
            )
        }
    )