package com.opencritic.games.details.reviews.outlet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.details.LoadingItem
import com.opencritic.games.details.reviews.IconTextItem
import com.opencritic.games.details.reviews.ReviewListItem
import com.opencritic.games.details.ui.OutletReviewsState
import com.opencritic.games.details.ui.OutletReviewsStateContent_PreviewData
import com.opencritic.mvvm.Spinner
import com.opencritic.resources.AndroidImageResourceProvider
import com.opencritic.resources.Icons
import com.opencritic.resources.asPainter
import com.opencritic.resources.defaultPadding

@Composable
fun OutletReviewsStateContent(
    state: OutletReviewsState.Content,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(defaultPadding),
        modifier = modifier
    ) {
        item {
            Card {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(defaultPadding)
                        .fillMaxWidth()
                ) {
                    SubcomposeAsyncImage(
                        model = state.outletImageUrl,
                        contentDescription = "",
                        loading = {
                            Box(
                                modifier = Modifier
                                    .background(Color.Gray)
                                    .size(width = 128.dp, height = 128.dp)
                                    .clip(shape = CircleShape)
                            )
                        },
                        modifier = Modifier
                            .size(width = 128.dp, height = 128.dp)
                            .clip(shape = CircleShape)
                    )

                    Text(
                        text = state.outletNameText,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    if (state.isHomepageVisible) {
                        TextButton(onClick = state.onHomepageClick) {
                            Text(text = state.homepageText)
                        }
                    }

                    Spacer(modifier = Modifier.height(defaultPadding))

                    state.infoItems.forEach {
                        IconTextItem(item = it)
                    }
                }
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = defaultPadding)
            ) {
                Text(text = state.sortTitleText)

                Spacer(modifier = Modifier.weight(1f))

                Spinner(
                    items = state.availableSorts,
                    selectedItem = state.sortText,
                    onItemSelected = {
                        state.onSelectedSort(it)
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
                    state.onLoadMore()
                }
            }
        }
    }
}

@Preview
@Composable
fun OutletReviewsStateContent_Preview() {
    OutletReviewsStateContent(
        state = OutletReviewsStateContent_PreviewData()
    )
}
