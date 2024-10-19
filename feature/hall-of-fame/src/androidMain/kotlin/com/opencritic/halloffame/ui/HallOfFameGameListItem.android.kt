package com.opencritic.halloffame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.GameRankItem
import com.opencritic.games.NoGamePoster
import com.opencritic.resources.smallPadding

@Composable
fun HallOfFameGameListItem(
    item: HallOfFameGameListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .clickable { item.onClick() }
    ) {
        SubcomposeAsyncImage(
            model = item.posterUrl,
            contentDescription = "",
            loading = {
                NoGamePoster(
                    modifier = Modifier
                        .size(width = 128.dp, height = 192.dp)
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            },
            error = {
                NoGamePoster(
                    modifier = Modifier
                        .size(width = 128.dp, height = 192.dp)
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            },
            modifier = Modifier
                .size(width = 128.dp, height = 192.dp)
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
        )

        GameRankItem(
            model = item.rank,
            modifier = Modifier
                .padding(top = smallPadding)
        )

        Text(
            text = item.nameText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .widthIn(0.dp, 128.dp)
                .heightIn(min = 48.dp)
        )
    }
}

@Preview
@Composable
fun HallOfFameGameListItem_Preview() {
    HallOfFameGameListItem(
        item = HallOfFameGameListItem_PreviewData()
    )
}