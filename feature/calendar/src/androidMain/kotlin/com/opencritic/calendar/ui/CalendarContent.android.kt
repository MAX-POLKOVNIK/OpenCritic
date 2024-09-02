package com.opencritic.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.text.text

@Composable
fun CalendarContent(
    content: CalendarContent,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(defaultPadding),
        verticalArrangement = Arrangement.spacedBy(defaultPadding)
    ) {
        item {
            Text(text = content.description.text())
        }

        content.cards.forEach { card ->
            item(key = card.id) {
                CalendarGameMonthCard(card)
            }
        }
    }
}

@Preview
@Composable
fun CalendarContent_Preview() {
    CalendarContent(
        content = CalendarContent_PreviewData()
    )
}