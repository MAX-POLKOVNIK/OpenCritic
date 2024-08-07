package com.opencritic.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.opencritic.dashboard.ui.DashboardMightyManItemListItem
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text

@Composable
fun DashboardMightyManItemListItem(
    item: DashboardMightyManItemListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Image(
            painter = item.imageResource.asPainter(),
            contentDescription = "",
            modifier = Modifier
                .width(56.dp)
                .height(56.dp)
        )
        Column {
            Text(
                text = item.name.text(),
                fontWeight = FontWeight.Bold,
            )
            Text(text = item.description.text())
        }
    }
}