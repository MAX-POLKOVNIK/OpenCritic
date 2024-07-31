package com.opencritic.games.details.reviews.author

import androidx.compose.foundation.background
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
import com.opencritic.games.details.ui.AuthorReviewsState
import com.opencritic.games.details.ui.AuthorReviewsStateContent_PreviewData
import com.opencritic.mvvm.Spinner
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun AuthorReviewsStateContent(
    state: AuthorReviewsState.Content,
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
                        model = state.imageUrl,
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
                        text = state.nameText,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(defaultPadding))

                    state.personalInfoItems.forEach {
                        IconTextItem(item = it)
                    }

                    Spacer(modifier = Modifier.height(defaultPadding))


                    if (state.isFavoritesGamesVisible) {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(bottom = defaultPadding)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Icon(
                                painter = Icons.heart.asPainter(),
                                contentDescription = "",
                                tint = Color.Red
                            )
                            Text(
                                text = state.favoritesGamesTitleText.text(),
                                modifier = Modifier
                                    .padding(start = smallPadding)
                            )
                        }

                        Column {
                            state.favoritesGames.forEach {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 32.dp)
                                )
                            }
                        }

                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = defaultPadding)
                        )
                    }

                    state.countersInfoItems.forEach {
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
                Text(text = state.sortTitleText.text())

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
                                text = item.name.text(),
                            )
                            Icon(
                                painter = Icons.arrowDown.asPainter(),
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.name.text())
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
fun AuthorReviewsStateContent_Preview() {
    AuthorReviewsStateContent(
        state = AuthorReviewsStateContent_PreviewData()
    )
}