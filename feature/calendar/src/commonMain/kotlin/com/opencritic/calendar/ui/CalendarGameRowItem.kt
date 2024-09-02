package com.opencritic.calendar.ui

data class CalendarGameRowItem(
    val cells: List<CalendarGameCellItem>,
) {
    fun switchedNext(): CalendarGameRowItem =
        copy(cells = cells.map { it.switchedNext() })
}

@Suppress("FunctionName")
fun CalendarGameRowItem_PreviewData(): CalendarGameRowItem =
    CalendarGameRowItem(
        cells = buildList {
            repeat(7) {
                add(
                    CalendarGameCellItem_PreviewData()
                )
            }
        }
    )