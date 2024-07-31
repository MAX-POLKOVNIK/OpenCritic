package com.opencritic.game.your

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.game.your.ui.YourGameIndicatorSmallItem
import com.opencritic.game.your.ui.YourGameIndicatorSmallItem_PreviewData
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.smallPadding
import com.opencritic.resources.toCompose

@Composable
fun YourGameIndicatorSmallItem(
    item: YourGameIndicatorSmallItem,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .height(intrinsicSize = IntrinsicSize.Max)
        ) {
            Image(
                painter = item.wantedImageResource.asPainter(),
                contentDescription = "",
                colorFilter = ColorFilter.tint(item.wantedIconColor?.toCompose() ?: LocalContentColor.current),
                modifier = Modifier
                    .size(36.dp)
                    .background(item.wantedBackgroundColor?.toCompose() ?: Color.Transparent)
                    .padding(smallPadding)
                    .clickable { item.wantedClick() }
            )

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
            )

            Image(
                painter = item.playedImageResource.asPainter(),
                contentDescription = "",
                colorFilter = ColorFilter.tint(item.playedIconColor?.toCompose() ?: LocalContentColor.current),
                modifier = Modifier
                    .size(36.dp)
                    .background(item.playedBackgroundColor?.toCompose() ?: Color.Transparent)
                    .padding(smallPadding)
                    .clickable { item.playedClick() }
            )

            VerticalDivider()

            Image(
                painter = item.favoriteImageResource.asPainter(),
                contentDescription = "",
                colorFilter = ColorFilter.tint(item.favoriteIconColor?.toCompose() ?: LocalContentColor.current),
                modifier = Modifier
                    .size(36.dp)
                    .background(item.favoriteBackgroundColor?.toCompose() ?: Color.Transparent)
                    .padding(smallPadding)
                    .clickable { item.favoriteClick() }
            )
        }
    }
}

@Preview
@Composable
fun YourGameIndicatorSmallItem_Preview() {
    YourGameIndicatorSmallItem(
        item = YourGameIndicatorSmallItem_PreviewData()
    )
}