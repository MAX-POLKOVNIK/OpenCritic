package com.opencritic.game.your

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.game.your.ui.YourGameIndicatorItem
import com.opencritic.game.your.ui.YourGameIndicatorItem_PreviewData
import com.opencritic.resources.AndroidImageResourceProvider
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.toCompose

@Composable
fun YourGameIndicatorItem(
    item: YourGameIndicatorItem,
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
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Max)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .background(item.wantedBackgroundColor.toCompose())
                    .clickable { item.wantedClick() }
                    .padding(defaultPadding)
            ) {
                Image(
                    painter = painterResource(id = item.wantedImageResource),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(item.wantedTextColor?.toCompose() ?: LocalContentColor.current),
                    modifier = Modifier
                        .size(38.dp)
                )
                Text(
                    text = item.wantedText,
                    color = item.wantedTextColor?.toCompose() ?: Color.Unspecified
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .background(item.playedBackgroundColor.toCompose())
                    .clickable { item.playedClick() }
                    .padding(defaultPadding)
            ) {
                Image(
                    painter = painterResource(id = item.playedImageResource),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(item.playedTextColor?.toCompose() ?: LocalContentColor.current),
                    modifier = Modifier
                        .size(38.dp)
                )
                Text(
                    text = item.playedText,
                    color = item.playedTextColor?.toCompose() ?: Color.Unspecified
                )
            }

            VerticalDivider()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .background(item.favoriteBackgroundColor.toCompose())
                    .clickable { item.favoriteClick() }
                    .padding(defaultPadding)
            ) {
                Image(
                    painter = painterResource(id = item.favoriteImageResource),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(item.favoriteTextColor?.toCompose() ?: LocalContentColor.current),
                    modifier = Modifier
                        .size(38.dp)
                )
                Text(
                    text = item.favoriteText,
                    color = item.favoriteTextColor?.toCompose() ?: Color.Unspecified
                )
            }
        }
    }
}

@Composable
@Preview
fun YourGameIndicatorItem_Preview() {
    YourGameIndicatorItem(
        YourGameIndicatorItem_PreviewData(AndroidImageResourceProvider())
    )
}