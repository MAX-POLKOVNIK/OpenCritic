package com.opencritic.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CalendarGameRowItem(
    item: CalendarGameRowItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        item.cells.forEach { cell ->
            CalendarGameCellItem(
                item = cell,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun CalendarGameRowItem_Preview() {
    CalendarGameRowItem(
        item = CalendarGameRowItem_PreviewData(),
    )
}