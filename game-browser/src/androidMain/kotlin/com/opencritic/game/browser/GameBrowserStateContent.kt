package com.opencritic.game.browser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencritic.game.browser.ui.GameBrowserState
import com.opencritic.game.browser.ui.GameBrowserStateContent_PreviewData
import com.opencritic.games.details.LoadingItem
import com.opencritic.mvvm.Spinner
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@Composable
fun GameBrowserStateContent(
    state: GameBrowserState.Content,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(defaultPadding),
        modifier = modifier,
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = state.platformTitleText)

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
                                text = item.text,
                            )
                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowDown,
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.text)
                    }
                )
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = state.timeframeTitleText)

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
                                text = item.text,
                            )
                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowDown,
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.text)
                    }
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
                                text = item.text,
                            )
                            Icon(
                                imageVector = Icons.Sharp.KeyboardArrowDown,
                                contentDescription = "drop down arrow"
                            )
                        }
                    },
                    dropdownItemFactory = { item, _ ->
                        Text(text = item.text)
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
fun GameBrowserStateContent_Preview() {
    GameBrowserStateContent(
        state = GameBrowserStateContent_PreviewData()
    )
}