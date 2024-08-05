package com.opencritic.calendar.ui

data class CalendarGameRowItem(
    val cells: List<CalendarGameCellItem>,
) {
    fun switchedNext(): CalendarGameRowItem =
        copy(cells = cells.map { it.switchedNext() })
}