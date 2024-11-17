package com.opencritic.calendar.ui

import com.opencritic.calendar.domain.GameMonth
import com.opencritic.mvvm.ListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class CalendarGameMonthCard(
    val nameText: String,
    val rows: ImmutableList<CalendarGameRowItem>,
    override val id: String = nameText,
) : ListItem<String> {
    fun switchedNext(): CalendarGameMonthCard =
        copy(rows = rows.map { it.switchedNext() }.toImmutableList())
}

fun CalendarGameMonthCard(
    month: GameMonth,
    onPosterClick: (CalendarGamePosterCellItem) -> Unit,
): CalendarGameMonthCard =
    CalendarGameMonthCard(
        nameText = month.name,
        rows = month.weeks.map { week ->
            CalendarGameRowItem(
                cells = week.days.mapIndexed { index, day ->
                    CalendarGameCellItem(
                        id = day?.dayNumber ?: (index * -1),
                        dayText = day?.dayNumber?.toString() ?: "",
                        isBackgroundVisible = day != null,
                        posters = day?.games?.map { game ->
                            CalendarGamePosterCellItem(
                                id = game.id,
                                name = game.name,
                                posterImageUrl = game.posterImageUrl,
                                onClick = onPosterClick
                            )
                        }?.toImmutableList() ?: persistentListOf()
                    )
                }.toImmutableList()
            )
        }.toImmutableList()
    )

@Suppress("FunctionName")
fun CalendarGameMonthCard_PreviewData(nameText: String = "September"): CalendarGameMonthCard =
    CalendarGameMonthCard(
        nameText = nameText,
        rows = List(5) {
            CalendarGameRowItem_PreviewData()
        }.toImmutableList()
    )