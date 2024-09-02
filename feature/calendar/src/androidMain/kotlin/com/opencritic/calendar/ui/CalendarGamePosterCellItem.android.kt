package com.opencritic.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.NoGamePoster

@Composable
fun CalendarGamePosterCellItem(
    item: CalendarGamePosterCellItem,
    modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = item.posterImageUrl,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        loading = {
            NoGamePoster(
                modifier = Modifier
                    .aspectRatio(9f / 16f)
            )
        },
        error = {
            NoGamePoster(
                modifier = Modifier
                    .aspectRatio(9f / 16f)
            )
        },
        modifier = modifier
            .aspectRatio(9f / 16f)
            .clickable { item.onClick() }
    )
}

@Preview
@Composable
fun CalendarGamePosterCellItem_Preview() {
    CalendarGamePosterCellItem(
        item = CalendarGamePosterCellItem_PreviewData()
    )
}