package com.opencritic.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardTitleListItem
import com.opencritic.resources.MyApplicationTheme
import com.opencritic.resources.defaultPadding

@Composable
fun DashboardTitleListItem(
    item: DashboardTitleListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = defaultPadding)
    ) {
        Text(
            text = item.titleText,
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = item.subtitleText)
    }
}

@Composable
@Preview
fun DashboardTitleListItem_Preview() {
    MyApplicationTheme {
        DashboardTitleListItem(
            item = DashboardTitleListItem(
                title = "Hello",
                subtitle = "Second string"
            )
        )
    }
}