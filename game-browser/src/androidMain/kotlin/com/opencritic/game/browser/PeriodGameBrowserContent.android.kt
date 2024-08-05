package com.opencritic.game.browser

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.game.browser.ui.period.PeriodGameBrowserContent
import com.opencritic.game.browser.ui.period.PeriodGameBrowserContent_PreviewData
import com.opencritic.games.details.LoadingItem
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@Composable
fun PeriodGameBrowserContent(
    state: PeriodGameBrowserContent,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(defaultPadding),
        modifier = modifier,
    ) {
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
fun PeriodGameBrowserStateContent_Preview() {
    PeriodGameBrowserContent(
        state = PeriodGameBrowserContent_PreviewData()
    )
}
