package com.opencritic.games.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.opencritic.games.details.ui.GameDetailsContent
import com.opencritic.games.details.ui.GameDetailsContent_PreviewData
import com.opencritic.games.details.ui.ScreenshotItem
import com.opencritic.games.details.ui.TrailerItem
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun GameDetailsContent(
    state: GameDetailsContent,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.EXPANDED -> GameDetailsExpanded(state = state)
            WindowWidthSizeClass.MEDIUM -> GameDetailsRegular(state = state)
            else -> GameDetailsCompact(state = state)
        }

        if (state.isMediaVisible) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = state.mediaText.text(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(defaultPadding)
            )

            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                state.media.forEachIndexed { index, mediaItem ->
                    when (mediaItem) {
                        is TrailerItem ->
                            TrailerItem(
                                item = mediaItem,
                                modifier = Modifier
                                    .padding(horizontal = defaultPadding)
                            )
                        is ScreenshotItem ->
                            ScreenshotItem(
                                item = mediaItem,
                                modifier = Modifier
                                    .padding(horizontal = defaultPadding)
                            )
                    }

                    if (index != state.media.lastIndex) {
                        Spacer(modifier = Modifier.height(smallPadding))
                    }
                }
            } else {
                LazyRow(
                    contentPadding = PaddingValues(defaultPadding),
                    horizontalArrangement = Arrangement.spacedBy(defaultPadding),
                    modifier = Modifier
                        .height(300.dp)
                ) {
                    state.media.forEach{ mediaItem ->
                        when (mediaItem) {
                            is TrailerItem ->
                                item {
                                    TrailerItem(
                                        item = mediaItem,
                                    )
                                }

                            is ScreenshotItem ->
                                item {
                                    ScreenshotItem(
                                        item = mediaItem,
                                    )
                                }
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(onClick = state.onViewAllMediaClick) {
                    Text(
                        text = state.viewAllMedia.text(),
                    )
                }
            }
        }

        if (state.isTrailersVisible) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = state.trailersText.text(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(defaultPadding)
            )

            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                state.trailers.forEachIndexed { index, mediaItem ->
                    TrailerItem(
                        item = mediaItem,
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                    )

                    if (index != state.trailers.lastIndex) {
                        Spacer(modifier = Modifier.height(smallPadding))
                    }
                }
            } else {
                LazyRow(
                    contentPadding = PaddingValues(defaultPadding),
                    horizontalArrangement = Arrangement.spacedBy(defaultPadding),
                    modifier = Modifier
                        .height(300.dp)
                ) {
                    state.trailers.forEach { mediaItem ->
                        item {
                            TrailerItem(
                                item = mediaItem,
                            )
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(onClick = state.onViewAllTrailersClick) {
                    Text(
                        text = state.viewAllTrailers.text(),
                    )
                }
            }
        }

        if (state.isScreenshotsVisible) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = state.screenshotsText.text(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(defaultPadding)
            )

            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                state.screenshots.forEachIndexed { index, mediaItem ->
                    ScreenshotItem(
                        item = mediaItem,
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                    )

                    if (index != state.screenshots.lastIndex) {
                        Spacer(modifier = Modifier.height(smallPadding))
                    }
                }
            } else {
                LazyRow(
                    contentPadding = PaddingValues(defaultPadding),
                    horizontalArrangement = Arrangement.spacedBy(defaultPadding),
                    modifier = Modifier
                        .height(300.dp)
                ) {
                    state.screenshots.forEach { mediaItem ->
                        item {
                            ScreenshotItem(
                                item = mediaItem,
                            )
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(onClick = state.onViewAllScreenshotsClick) {
                    Text(
                        text = state.viewAllScreenshots.text(),
                    )
                }
            }
        }

        if (state.isReviewsVisible) {
            Text(
                text = state.reviewTitleText.text(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(defaultPadding)
            )

            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                state.reviews.forEachIndexed { index, review ->
                    CardReviewItem(
                        item = review,
                        modifier = Modifier
                            .padding(horizontal = defaultPadding)
                    )

                    if (index != state.reviews.lastIndex) {
                        Spacer(modifier = Modifier.height(smallPadding))
                    }
                }
            } else {
                NonLazyGrid(
                    columns = 2,
                    itemCount = state.reviews.size,
                    spacing = 16.dp,
                    modifier = Modifier
                        .padding(horizontal = smallPadding)
                ) {
                    CardReviewItem(
                        item = state.reviews[it]
                    )
                }
            }

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

        Spacer(modifier = Modifier.height(defaultPadding))
    }

    LaunchedEffect(Unit) {
        state.onRefresh()
    }
}

@Composable
@Preview
fun GameDetailsStateContent_Preview() {
    GameDetailsContent(
        state = GameDetailsContent_PreviewData()
    )
}