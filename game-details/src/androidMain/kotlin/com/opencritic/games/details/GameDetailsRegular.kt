package com.opencritic.games.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.game.your.lists.YourGameIndicatorItem
import com.opencritic.games.details.ui.GameDetailsContent
import com.opencritic.games.details.ui.GameDetailsContent_PreviewData
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text

@Composable
fun GameDetailsRegular(
    state: GameDetailsContent,
    modifier: Modifier = Modifier,
) {
    Column {
        Box(
            contentAlignment = Alignment.BottomEnd,
        ) {
            SubcomposeAsyncImage(
                model = state.bannerImageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )

            YourGameIndicatorItem(
                item = state.yourGameIndicatorItem,
                modifier = Modifier
                    .padding(defaultPadding)
                    .width(350.dp)
            )
        }

        Card(
            modifier = Modifier
                .padding(defaultPadding)
        ) {
            Text(
                text = state.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = defaultPadding)
                    .padding(top = defaultPadding)
            )

            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = state.companiesText,
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                    )

                    Text(
                        text = state.releaseDateText.text(),
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                    )

                    Text(
                        text = state.platformsText,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                    )

                    Spacer(modifier = Modifier.height(defaultPadding))

                    if (state.isTierVisible) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = defaultPadding)
                                .fillMaxWidth()
                        ) {
                            Image(
                                painter = state.tierImageResource.asPainter(),
                                contentDescription = "",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(76.dp)
                            )
                            RankCircleIndicatorItem(
                                item = state.topCriticScore,
                                modifier = Modifier
                                    .size(76.dp)
                            )
                            RankCircleIndicatorItem(
                                item = state.recommendedPercent,
                                modifier = Modifier
                                    .size(76.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .padding(horizontal = defaultPadding)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = state.tierDescription.text(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = state.topCriticScoreDescription.text(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = state.criticsRecommendDescription.text(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    state.briefReviews.forEach {
                        ReviewBriefListItem(item = it)
                    }

                    if (state.isViewAllVisible) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TextButton(onClick = state.onViewAllReviewsClick) {
                                Text(
                                    text = state.viewAllText.text(),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GameDetailsRegular_Preview() {
    GameDetailsRegular(
        state = GameDetailsContent_PreviewData()
    )
}