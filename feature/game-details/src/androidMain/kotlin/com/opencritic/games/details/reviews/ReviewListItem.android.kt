package com.opencritic.games.details.reviews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.opencritic.games.details.ui.ReviewListItem
import com.opencritic.games.details.ui.ReviewListItem_PreviewData
import com.opencritic.games.details.ui.ReviewScoreDisplayItem
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.text.text

@Composable
fun ReviewListItem(
    item: ReviewListItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = item.authorText,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .clickable { item.authorClick() }
                )
                Text(
                    text = item.outletText,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .clickable { item.outletClick() }
                )
            }

            SubcomposeAsyncImage(
                model = item.imageUrl,
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
                    .clickable { item.imageClick() }
                    .clip(
                        shape = CircleShape
                    )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
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

            Text(text = item.dateText.text())
        }

        if (item.isGameVisible) {
            TextButton(
                onClick = item::gameClick,
                modifier = Modifier
                    .padding(top = defaultPadding)
            ) {
                Text(text = item.gameText)
            }
        }

        if (item.isYoutubeVisible) {
            Card(
                onClick = item::click,
                modifier = modifier,
            ) {
                SubcomposeAsyncImage(
                    model = requireNotNull(item.youtubePlaceholderUrl),
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

        if (item.isSnipperVisible) {
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
        ) {
            Text(text = item.readFullReviewText.text())
        }
    }
}

@Composable
@Preview
fun ReviewListItem_Preview() {
    ReviewListItem(
        item = ReviewListItem_PreviewData("1")
    )
}
