package com.opencritic.games.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.details.ui.TrailerItem
import com.opencritic.resources.defaultPadding

@Composable
fun TrailerItem(
    item: TrailerItem,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = item::click,
        modifier = modifier
            .aspectRatio(13f / 9f),
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
                .aspectRatio(16f / 9f)
                .clipToBounds()
        )

        Text(
            text = item.titleText,
            modifier = Modifier
                .padding(defaultPadding)
        )
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