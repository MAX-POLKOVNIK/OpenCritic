package com.opencritic.game.your.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.game.your.ui.list.GameRowListItem
import com.opencritic.game.your.ui.list.GameRowListItem_PreviewData
import com.opencritic.games.GameRankItem
import com.opencritic.resources.defaultPadding

@Composable
fun GameRowListItem(
    item: GameRowListItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
    ) {
        SubcomposeAsyncImage(
            model = item.posterUrl,
            contentDescription = "",
            loading = {
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .size(width = 64.dp, height = 96.dp)
                        .clip(
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            },
            modifier = Modifier
                .size(width = 64.dp, height = 96.dp)
                .clip(
                    shape = RoundedCornerShape(4.dp)
                )
        )

        Column(
            modifier = Modifier
                .padding(defaultPadding)
        ) {
            Text(text = item.name)

            GameRankItem(model = item.rank, modifier = Modifier.padding(top = defaultPadding))
        }
    }
}

@Preview
@Composable
fun GameRowListItem_Preview() {
    GameRowListItem(
        item = GameRowListItem_PreviewData()
    )
}