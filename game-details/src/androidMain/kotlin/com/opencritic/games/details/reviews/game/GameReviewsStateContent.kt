package com.opencritic.games.details.reviews.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.details.LoadingItem
import com.opencritic.games.details.RankCircleIndicatorItem
import com.opencritic.games.details.reviews.ReviewListItem
import com.opencritic.games.details.ui.GameReviewsState
import com.opencritic.games.details.ui.GameReviewsStateContent_PreviewData
import com.opencritic.mvvm.Spinner
import com.opencritic.resources.Icons
import com.opencritic.resources.asPainter
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@Composable
fun GameReviewsStateContent(
    state: GameReviewsState.Content,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(defaultPadding),
        modifier = modifier
    ) {
        item {
            Card {
                Box(
                    contentAlignment = Alignment.BottomStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                ) {
                    SubcomposeAsyncImage(
                        model = state.imageUrl,
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(smallPadding),
                        modifier = Modifier
                            .padding(defaultPadding)
                    ) {
                        if (state.isTierVisible) {
                            Image(
                                painter = state.tierImageResource.asPainter(),
                                contentDescription = "",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(56.dp)
                            )
                        }

                        if (state.isTopScoreIndicatorVisible) {
                            RankCircleIndicatorItem(
                                item = state.topScoreIndicator,
                                modifier = Modifier
                                    .size(56.dp)
                            )
                        }

                        if (state.isRecommendIndicatorVisible) {
                            RankCircleIndicatorItem(
                                item = state.recommendScoreIndicator,
                                modifier = Modifier
                                    .size(56.dp)
                            )
                        }
                    }
                }
            }
        }

        if (state.isRankedDescriptionVisible) {
            item {
                Text(
                    text = state.rankedDescription,
                    modifier = Modifier
                        .padding(vertical = defaultPadding)
                )
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = state.sortTitleText)

                Spacer(modifier = Modifier.weight(1f))

                Spinner(
                    items = state.availableSorts,
                    selectedItem = state.sortText,
                    onItemSelected = {
                        state.selectedSort(it)
                    },
                    selectedItemFactory = { modifier, item ->
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = modifier
                                .padding(8.dp)
                        ) {
                            Text(
                                text = item.name,
                            )
                            Icon(
                                painter = Icons.arrowDown.asPainter(),
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.name)
                    }
                )
            }
        }

        state.reviewItems.forEach {
            item {
                HorizontalDivider()

                ReviewListItem(
                    item = it,
                    modifier = Modifier
                        .padding(top = defaultPadding)
                )
            }
        }

        if (state.isLoadingItemVisible) {
            item {
                LoadingItem(
                    item = state.loadingItem,
                )
                LaunchedEffect(Unit) {
                    state.loadMore()
                }
            }
        }
    }
}

@Preview
@Composable
fun GameReviewsStateContent_Preview() {
    GameReviewsStateContent(
        state = GameReviewsStateContent_PreviewData()
    )
}