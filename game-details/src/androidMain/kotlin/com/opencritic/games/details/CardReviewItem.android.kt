package com.opencritic.games.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.details.ui.CardReviewItem
import com.opencritic.games.details.ui.ReviewScoreDisplayItem
import com.opencritic.resources.Icons
import com.opencritic.resources.R
import com.opencritic.resources.asPainter
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.smallPadding

@Composable
fun CardReviewItem(
    item: CardReviewItem,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(defaultPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = item.outletText,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = item.authorText
                    )
                }

                SubcomposeAsyncImage(
                    model = item.outletThumbnailUrl,
                    contentDescription = "",
                    loading = {
                        Box(
                            modifier = Modifier
                                .background(Color.Gray)
                                .size(width = 56.dp, height = 56.dp)
                                .clip(
                                    shape = CircleShape
                                )
                        )
                    },
                    modifier = Modifier
                        .size(width = 48.dp, height = 48.dp)
                        .clip(
                            shape = CircleShape
                        )
                )
            }

            when (item.score) {
                is ReviewScoreDisplayItem.Stars -> {
                    Row {
                        repeat(item.score.filledStars) {
                            Icon(
                                painter = Icons.starFilled.asPainter(),
                                contentDescription = ""
                            )
                        }
                        repeat(item.score.halfStars) {
                            Icon(
                                painter = Icons.starHalf.asPainter(),
                                contentDescription = ""
                            )
                        }
                        repeat(item.score.emptyStars) {
                            Icon(
                                painter = Icons.star.asPainter(),
                                contentDescription = ""
                            )
                        }
                    }
                }
                is ReviewScoreDisplayItem.String -> {
                    Text(
                        text = item.score.value,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Text(
                text = item.snippetText,
                modifier = Modifier
                    .padding(top = defaultPadding)
            )


        }

        TextButton(
            onClick = item::click,
            modifier = Modifier
                .padding(top = defaultPadding)
                .padding(start = smallPadding)
        ) {
            Text(text = item.readFullReviewText)
        }
    }
}

@Composable
@Preview
fun CardReviewItem_Preview() {
    CardReviewItem(
        item = CardReviewItem(
            id = "",
            outletText = "GamesRadar+",
            authorText = "Sam Loveridge",
            outletThumbnailUrl = "https://img.opencritic.com/outlet/91/qqIbhWtu.jpg",
            score = ReviewScoreDisplayItem.Stars(
                filledStars = 4,
                halfStars = 1,
                emptyStars = 5
            ),
            snippetText = "Everything that made GT Sport so good, plus everything that made early Gran Turismo games so good. A simply stunning driving game and a superb showcase for PS5.",
            readFullReviewText = "Read full review",
            onClick = {}
        )
    )
}