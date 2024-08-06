package com.opencritic.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.resources.defaultPadding

@Composable
fun CalendarGameMonthCard(
    item: CalendarGameMonthCard,
    modifier: Modifier = Modifier,
) {
    Card {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = item.nameText,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = defaultPadding)
            )

            item.rows.forEach { row ->
                CalendarGameRowItem(
                    item = row
                )
            }
        }
    }
}

@Preview
@Composable
fun CalendarGameMonthCard_Preview() {
    CalendarGameMonthCard(
        item = CalendarGameMonthCard_PreviewData()
    )
}
