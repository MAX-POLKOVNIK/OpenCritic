package com.opencritic.game.browser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.game.browser.ui.BrowseGameItem
import com.opencritic.game.browser.ui.BrowseGameItem_PreviewData
import com.opencritic.games.details.RankCircleIndicatorItem
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.images.Icons
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun BrowseGameItem(
    item: BrowseGameItem,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { item.onClick() },
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            SubcomposeAsyncImage(
                model = item.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                            .fillMaxSize()
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                        )
                    )
            )

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(smallPadding),
                    modifier = Modifier
                        .padding(horizontal = defaultPadding)
                ) {
                    if (item.isTierVisible) {
                        Image(
                            painter = item.tierImageResource.asPainter(),
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .width(56.dp)
                        )
                    }

                    if (item.isTopCriticVisibleIndicator) {
                        RankCircleIndicatorItem(
                            item = item.topCriticScoreIndicator,
                            modifier = Modifier
                                .size(56.dp)
                        )
                    }

                    if (item.isPercentRecommendedIndicatorVisible) {
                        RankCircleIndicatorItem(
                            item = item.percentRecommendedIndicator,
                            modifier = Modifier
                                .size(56.dp)
                        )
                    }
                }
                Text(
                    text = item.nameText,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = defaultPadding)
                        .padding(vertical = smallPadding)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = defaultPadding)
                        .padding(bottom = defaultPadding)
                ) {
                    Icon(
                        painter = Icons.calendar.asPainter(),
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        text = item.dateText.text(),
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(horizontal = smallPadding)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun BrowseGameItem_Preview() {
    BrowseGameItem(item = BrowseGameItem_PreviewData())
}