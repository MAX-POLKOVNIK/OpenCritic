package com.opencritic.games.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.game.your.lists.YourGameIndicatorItem
import com.opencritic.games.NoGamePoster
import com.opencritic.games.details.ui.GameDetailsContent
import com.opencritic.games.details.ui.GameDetailsContent_PreviewData
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun GameDetailsCompact(
    state: GameDetailsContent,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .padding(defaultPadding),
        ) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                SubcomposeAsyncImage(
                    model = state.squareImageUrl,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    loading = {
                        NoGamePoster()
                    },
                    error = {
                        NoGamePoster()
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )

                if (state.isTierVisible) {
                    Image(
                        painter = state.tierImageResource.asPainter(),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .padding(defaultPadding)
                            .width(76.dp)
                    )
                }
            }

            YourGameIndicatorItem(
                item = state.yourGameIndicatorItem,
                modifier = Modifier,
                isInCard = true,
            )

            if (state.isTierVisible) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = smallPadding,
                            vertical = defaultPadding,
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        RankCircleIndicatorItem(
                            item = state.topCriticScore,
                            modifier = Modifier
                                .size(76.dp)
                        )

                        Text(
                            text = state.topCriticScoreDescription.text(),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        RankCircleIndicatorItem(
                            item = state.recommendedPercent,
                            modifier = Modifier
                                .size(76.dp)
                        )

                        Text(
                            text = state.criticsRecommendDescription.text(),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        RankCircleIndicatorItem(
                            item = state.playerRating,
                            modifier = Modifier
                                .size(76.dp)
                                .clickable(onClick = state.onGameRatingClick)
                        )

                        Text(
                            text = state.playerRatingDescription.text(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clickable(onClick = state.onGameRatingClick)
                        )
                    }
                }
            }

            state.briefReviews.forEach {
                ReviewBriefListItem(
                    item = it,
                    modifier = Modifier
                        .padding(horizontal = defaultPadding)
                )
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

        Text(
            text = state.creatorsText.text(),
            modifier = Modifier
                .padding(top = smallPadding)
                .padding(horizontal = defaultPadding)
        )

        Text(
            text = state.releaseText.text(),
            modifier = Modifier
                .padding(horizontal = defaultPadding)
        )

        Text(
            text = state.platformsText.text(),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(bottom = smallPadding)
                .padding(horizontal = defaultPadding)
        )
    }
}

@Preview
@Composable
fun GameDetailsCompact_Preview() {
    GameDetailsCompact(
        state = GameDetailsContent_PreviewData()
    )
}
