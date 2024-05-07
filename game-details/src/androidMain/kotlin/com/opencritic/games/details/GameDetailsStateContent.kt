package com.opencritic.games.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.game.your.YourGameIndicatorItem
import com.opencritic.games.details.ui.GameDetailsState
import com.opencritic.games.details.ui.GameDetailsStateContent_PreviewData
import com.opencritic.resources.AndroidImageResourceProvider
import com.opencritic.resources.defaultPadding

@Composable
fun GameDetailsStateContent(state: GameDetailsState.Content, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        if (state.isImageVisible) {
            SubcomposeAsyncImage(
                model = state.imageUrl,
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
                    .aspectRatio(1f)
            )

            YourGameIndicatorItem(
                item = state.yourGameIndicatorItem,
                modifier = Modifier
                    .padding(defaultPadding)
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .padding(horizontal = defaultPadding)
            ) {
                Column {
                    Text(
                        text = state.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                            .padding(top = defaultPadding)
                    )

                    Text(
                        text = state.companiesText,
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                    )

                    Text(
                        text = state.releaseDateText,
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
                                painter = painterResource(id = state.tierImageResource),
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
                                text = state.tierDescription,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = state.topCriticScoreDescription,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = state.criticsRecommendDescription,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(defaultPadding))

                    state.briefReviews.forEach {
                        ReviewBriefListItem(item = it)
                    }

                    if (state.isViewAllVisible) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TextButton(onClick = state::viewAllReviewsClick) {
                                Text(
                                    text = state.viewAllText,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun GameDetailsStateContent_Preview() {
    GameDetailsStateContent(
        state = GameDetailsStateContent_PreviewData(AndroidImageResourceProvider())
    )
}