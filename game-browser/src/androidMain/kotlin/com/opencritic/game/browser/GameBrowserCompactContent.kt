package com.opencritic.game.browser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.game.browser.ui.GameBrowserContent
import com.opencritic.game.browser.ui.GameBrowserContent_PreviewData
import com.opencritic.games.details.LoadingItem
import com.opencritic.mvvm.Spinner
import com.opencritic.mvvm.rememberForeverLazyListState
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun GameBrowserCompactContent(
    state: GameBrowserContent,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = rememberForeverLazyListState(key = "GameBrowserStateContent"),
        contentPadding = PaddingValues(defaultPadding),
        modifier = modifier,
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = state.platformTitleText.text())

                Spacer(modifier = Modifier.weight(1f))

                Spinner(
                    items = state.platformsItems,
                    selectedItem = state.platformText,
                    onItemSelected = {
                        state.onSelectedPlatform(it)
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
                Text(text = state.timeframeTitleText.text())

                Spacer(modifier = Modifier.weight(1f))

                Spinner(
                    items = state.timeframeItems,
                    selectedItem = state.timeframeText,
                    onItemSelected = {
                        state.onSelectedTimeframe(it)
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
                    items = state.sortItems,
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

        state.browseGameItems.forEach {
            item {
                BrowseGameItem(
                    item = it,
                    modifier = Modifier
                        .padding(top = smallPadding)
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
fun GameBrowserCompactContent_Preview() {
    GameBrowserCompactContent(
        state = GameBrowserContent_PreviewData()
    )
}