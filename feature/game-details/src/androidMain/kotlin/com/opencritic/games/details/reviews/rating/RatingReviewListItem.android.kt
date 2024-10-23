package com.opencritic.games.details.reviews.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.opencritic.games.details.ui.ReviewScoreDisplayItem
import com.opencritic.games.details.ui.reviews.rating.RatingReviewListItem
import com.opencritic.games.details.ui.reviews.rating.RatingReviewListItem_PreviewData
import com.opencritic.resources.defaultPadding
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.asPainter
import com.opencritic.resources.smallPadding
import com.opencritic.resources.text.text

@Composable
fun RatingReviewListItem(
    item: RatingReviewListItem,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
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

            Icon(
                painter = item.icon.asPainter(),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = defaultPadding)
            )

            Text(
                text = item.userName,
                modifier = Modifier
                    .padding(start = smallPadding)
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
        }

        Text(
            text = item.dateText.text(),
            modifier = Modifier
                .padding(vertical = smallPadding)
        )

        Text(
            text = item.userText
        )

        Row {
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            TextButton(
                enabled = false,
                onClick = {},
            ) {
                Text(text = item.readFullReviewText.text())
            }
        }
    }
}

@Preview
@Composable
fun RatingReviewListItem_Preview() {
    RatingReviewListItem(item = RatingReviewListItem_PreviewData())
}