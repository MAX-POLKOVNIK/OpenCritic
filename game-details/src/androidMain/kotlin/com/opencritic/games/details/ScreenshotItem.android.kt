package com.opencritic.games.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.details.ui.ScreenshotItem

@Composable
fun ScreenshotItem(
    item: ScreenshotItem,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = item::click,
        modifier = modifier,
    ) {
        SubcomposeAsyncImage(
            model = item.thumbnailUrl,
            contentDescription = "",
            loading = {
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .aspectRatio(16f / 9f)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clipToBounds()
        )
    }
}

@Preview
@Composable
fun ScreenshotItem_Preview() {
    ScreenshotItem(
        item = ScreenshotItem(
            thumbnailUrl = "https://i.stack.imgur.com/A5oSb.png",
            onClick = {},
        )
    )
}