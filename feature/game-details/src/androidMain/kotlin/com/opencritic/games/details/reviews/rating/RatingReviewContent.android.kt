package com.opencritic.games.details.reviews.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.NoGamePoster
import com.opencritic.games.details.LoadingItem
import com.opencritic.games.details.ui.reviews.rating.RatingReviewsState
import com.opencritic.games.details.ui.reviews.rating.RatingReviewsStateContent_PreviewData
import com.opencritic.mvvm.Spinner
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.text.text

@Composable
fun RatingReviewsStateContent(
    state: RatingReviewsState.Content,
    modifier: Modifier = Modifier
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
                            NoGamePoster(
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        },
                        error = {
                            NoGamePoster(
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = defaultPadding)
            ) {
                Text(text = state.timeFrameTitle.text())

                Spacer(modifier = Modifier.weight(1f))

                Spinner(
                    items = state.availableTimeFrames,
                    selectedItem = state.selectedTimeFrameItem,
                    onItemSelected = {
                        state.selectedTimeFrame(it)
                    },
                    selectedItemFactory = { modifier, item ->
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = modifier
                                .padding(8.dp)
                        ) {
                            Text(
                                text = item.text.text(),
                            )
                            Icon(
                                painter = Icons.arrowDown.asPainter(),
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.text.text())
                    }
                )
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = state.sortTitleText.text())

                Spacer(modifier = Modifier.weight(1f))

                Spinner(
                    items = state.availableSorts,
                    selectedItem = state.selectedSortItem,
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
                                text = item.text.text(),
                            )
                            Icon(
                                painter = Icons.arrowDown.asPainter(),
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.text.text())
                    }
                )
            }
        }

        state.reviewItems.forEach {
            item {
                HorizontalDivider()

                RatingReviewListItem(
                    item = it,
                    modifier = Modifier
                        .padding(top = defaultPadding)
                        .padding(horizontal = defaultPadding)
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
fun RatingReviewsStateContent_Preview() {
    RatingReviewsStateContent(
        state = RatingReviewsStateContent_PreviewData()
    )
}