package com.opencritic.games.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.details.ui.TrailerItem
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.largePadding

@Composable
fun TrailerItem(
    item: TrailerItem,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = item::click,
        modifier = modifier
            .aspectRatio(16f / 9f),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
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
                error = {
                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                            .aspectRatio(16f / 9f)
                    )
                },
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .clipToBounds()
            )

            Image(
                painter = Icons.videoPlay.asPainter(),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.LightGray),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(largePadding)
            )
        }
    }
}

@Preview
@Composable
fun TrailerItem_Preview() {
    TrailerItem(
        item = TrailerItem(
            titleText = "Trailer text",
            thumbnailUrl = "https://img.youtube.com/vi/uLN9qrJ8ESs/0.jpg",
            onClick = {},
        )
    )
}