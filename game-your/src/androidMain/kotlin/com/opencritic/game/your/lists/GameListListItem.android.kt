package com.opencritic.game.your.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.game.your.ui.lists.GameListListItem
import com.opencritic.game.your.ui.lists.GameListListItem_PreviewData
import com.opencritic.games.NoGamePoster
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun GameListListItem(
    item: GameListListItem,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = item.onClick,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding)
        ) {
            Box(
                modifier = Modifier
                    .width(width = 320.dp)
                    .height(height = 192.dp)
            ) {
                item.posterUrls.reversed().forEachIndexed { index, posterUrl ->
                    val padding = ((item.posterUrls.size - 1 - index) * 64)
                    Box(
                        modifier = Modifier
                            .padding(start = padding.dp)
                    ) {
                        SubcomposeAsyncImage(
                            model = posterUrl,
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
                    }
                }
            }

            Text(
                text = item.nameText.text(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = defaultPadding)
            )

            Text(
                text = item.gamesText.text(),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(defaultPadding),
                modifier = Modifier.padding(top = smallPadding)
            ) {
                Button(onClick = item.onShareClick) {
                    Text(text = item.shareButtonText.text())
                }

                Button(
                    onClick = item.onEditClick,
                    enabled = false,
                ) {
                    Text(text = item.editButtonText.text())
                }
            }
        }
    }
}

@Preview
@Composable
fun GameListListItem_Preview() {
    GameListListItem(item = GameListListItem_PreviewData())
}