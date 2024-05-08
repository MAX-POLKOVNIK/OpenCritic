package com.opencritic.game.your

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.game.your.ui.YourGameListItem
import com.opencritic.game.your.ui.YourGameListItem_PreviewData
import com.opencritic.resources.AndroidImageResourceProvider

@Composable
fun YourGameListItem(
    item: YourGameListItem,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.click() }
    ) {
        Text(
            text = item.text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
        )

        YourGameIndicatorSmallItem(item = item.indicatorItem)
    }
}

@Preview
@Composable
fun YourGameListItem_Preview() {
    YourGameListItem(
        item = YourGameListItem_PreviewData(AndroidImageResourceProvider())
    )
}