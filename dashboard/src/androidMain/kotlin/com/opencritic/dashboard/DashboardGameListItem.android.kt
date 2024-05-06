package com.opencritic.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardGameListItem
import com.opencritic.dashboard.ui.DashboardGameListItem_PreviewData
import com.opencritic.games.GameRankItem
import com.opencritic.resources.AndroidImageResourceProvider
import com.opencritic.resources.defaultPadding

@Composable
fun DashboardGameListItem(
    item: DashboardGameListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.click() }
            .padding(horizontal = defaultPadding)
            .heightIn(min = 40.dp)
    ) {
        GameRankItem(model = item.rank)
        Text(
            text = item.nameText,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(1f)
                .padding(end = defaultPadding),
        )
        Text(
            text = item.dateText,
            modifier = Modifier
                .widthIn(min = 70.dp)
        )
    }
}

@Composable
@Preview
fun DashboardGameListItem_Preview() {
    DashboardGameListItem(
        item = DashboardGameListItem_PreviewData(AndroidImageResourceProvider())
    )
}