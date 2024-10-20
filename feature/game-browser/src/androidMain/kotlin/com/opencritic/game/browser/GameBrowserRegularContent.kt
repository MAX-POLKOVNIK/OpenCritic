package com.opencritic.game.browser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
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
fun GameBrowserRegularContent(
    state: GameBrowserContent,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
) {
    val cellCount = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.MEDIUM -> 2
        WindowWidthSizeClass.EXPANDED -> 3
        else -> 1
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(cellCount),
        contentPadding = PaddingValues(defaultPadding),
        verticalArrangement = Arrangement.spacedBy(defaultPadding),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding),
        modifier = modifier,
    ) {
        item(span = { GridItemSpan(cellCount) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = state.platformTitleText.text())

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

                Spacer(modifier = Modifier.width(defaultPadding))

                Text(text = state.timeframeTitleText.text())

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

                Spacer(modifier = Modifier.width(defaultPadding))

                Text(text = state.sortTitleText.text())

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

                if (state.isNextGenVisible) {
                    Spacer(modifier = Modifier.width(defaultPadding))

                    Text(text = state.nextGenTitle.text())

                    Checkbox(
                        checked = state.isNextGenChecked,
                        onCheckedChange = { isChecked ->
                            state.onNextGenChecked(isChecked)
                        }
                    )
                }
            }
        }

        state.browseGameItems.forEach {
            item {
                BrowseGameItem(
                    item = it,
                )
            }
        }

        if (state.isLoadingItemVisible) {
            item(span = { GridItemSpan(cellCount) }) {
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
fun GameBrowserRegularContent_Preview() {
    GameBrowserRegularContent(
        state = GameBrowserContent_PreviewData()
    )
}