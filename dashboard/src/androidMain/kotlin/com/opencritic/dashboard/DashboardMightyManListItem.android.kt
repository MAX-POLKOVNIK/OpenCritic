package com.opencritic.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardMightyManListItem
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.text.text

@Composable
fun DashboardMightyManListItem(
    item: DashboardMightyManListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        HorizontalDivider()

        Column(
            modifier = Modifier
                .padding(all = defaultPadding)
        ) {


            Text(
                text = item.title.text(),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.description.text())
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.colorDescription.text())

            item.items.forEach {
                DashboardMightyManItemListItem(item = it)
            }
        }

        HorizontalDivider()
    }
}
