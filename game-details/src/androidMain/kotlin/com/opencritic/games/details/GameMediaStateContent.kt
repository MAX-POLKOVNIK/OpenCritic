package com.opencritic.games.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.opencritic.games.details.ui.GameMediaState
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@Composable
fun GameMediaStateContent(
    state: GameMediaState.Content,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = defaultPadding)
    ) {
        if (state.isTrailersVisible) {
            Text(
                text = state.trailersText,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(defaultPadding)
            )

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
        }

        if (state.isScreenshotsVisible) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = state.screenshotsText,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(defaultPadding)
            )

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
        }
    }
}