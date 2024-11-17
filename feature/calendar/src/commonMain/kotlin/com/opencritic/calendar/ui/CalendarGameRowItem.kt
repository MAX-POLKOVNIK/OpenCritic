package com.opencritic.calendar.ui

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class CalendarGameRowItem(
    val cells: ImmutableList<CalendarGameCellItem>,
) {
    fun switchedNext(): CalendarGameRowItem =
        copy(cells = cells.map { it.switchedNext() }.toImmutableList())
}

@Suppress("FunctionName")
fun CalendarGameRowItem_PreviewData(): CalendarGameRowItem =
    CalendarGameRowItem(
        cells = List(7) {
            CalendarGameCellItem_PreviewData()
        }.toImmutableList()
    )