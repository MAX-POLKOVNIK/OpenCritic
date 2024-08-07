package com.opencritic.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardTitleListItem
import com.opencritic.resources.MyApplicationTheme
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.text

@Composable
fun DashboardTitleListItem(
    item: DashboardTitleListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = defaultPadding)
        ) {
            Text(
                text = item.titleText.text(),
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = item.subtitleText.text())
        }

        if (item.buttonTitle != null) {
            TextButton(onClick = item.onButtonClick) {
                Text(item.buttonTitle.text())
            }
        }
    }
}

@Composable
@Preview
fun DashboardTitleListItem_Preview() {
    MyApplicationTheme {
        DashboardTitleListItem(
            item = DashboardTitleListItem(
                title = "Hello".asTextSource(),
                subtitle = "Second string".asTextSource(),
                buttonTitle = "View all".asTextSource(),
            ),
        )
    }
}