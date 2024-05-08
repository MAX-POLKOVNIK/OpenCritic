package com.opencritic.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.opencritic.dashboard.ui.DashboardSublistListItem
import com.opencritic.resources.defaultPadding

@Composable
fun DashboardSublistListItem(
    item: DashboardSublistListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = item.titleText,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = defaultPadding)
        )

        HorizontalDivider()

        item.items.forEach {
            DashboardGameListItem(item = it)
            HorizontalDivider()
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(onClick = item::moreClick) {
                Text(
                    text = item.viewMoreText,
                    modifier = Modifier
                        .padding(horizontal = defaultPadding)
                )
            }
        }
    }
}