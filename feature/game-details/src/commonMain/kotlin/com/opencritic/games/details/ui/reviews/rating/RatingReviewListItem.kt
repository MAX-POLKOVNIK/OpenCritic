package com.opencritic.games.details.ui.reviews.rating

import com.opencritic.games.ReviewScoreFormat
import com.opencritic.games.details.domain.GameRatingReview
import com.opencritic.games.details.ui.ReviewScoreDisplayItem
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.Format
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class RatingReviewListItem(
    override val id: String,
    val icon: IconResource,
    val userText: String,
    val userName: String,
    val score: ReviewScoreDisplayItem,
    val dateText: TextSource,
    val readFullReviewText: TextSource,
) : ListItem<String>

fun RatingReviewListItem(ratingReview: GameRatingReview): RatingReviewListItem =
    RatingReviewListItem(
        id = ratingReview.id,
        icon = Icons.userIcon,
        userName = ratingReview.user.name,
        userText = ratingReview.summary,
        dateText = ratingReview.date.toLocalDateTime(TimeZone.UTC).date format Format.Medium,
        readFullReviewText = StringRes.str_read_full_review.asTextSource(),
        score = ReviewScoreDisplayItem(
            score = ratingReview.score,
            scoreFormat = ReviewScoreFormat(
                id = 19,
                name = "Stars",
                shortName = "stars",
                scoreDisplay = null,
                isNumeric = false,
                isSelect = false,
                isStars = true,
                isPercent = false,
                numDecimals = null,
                base = 20,
                options = null,
            )
        )
    )

@Suppress("FunctionName")
fun RatingReviewListItem_PreviewData(id: String = ""): RatingReviewListItem =
    RatingReviewListItem(
        id = id,
        icon = Icons.userIcon,
        userText = "This explosive campaign is one of the most fun, intricate, diverse single player FPS games ever. It's just top notch from beginning to end. Huge set pieces, amazing gunplay, avenues to upgrade aspects without getting to deep and absolutely stunning graphics / performance. Don't miss out on this game, it's one of the best campaigns for single player FPS games out there!",
        userName = "Max Polkovnik",
        dateText = "Nov 17, 2024".asTextSource(),
        score = ReviewScoreDisplayItem.Stars(
            filledStars = 1,
            halfStars = 1,
            emptyStars = 3,
        ),
        readFullReviewText = "Read Full Review".asTextSource()
    )

internal fun List<RatingReviewListItem>.mapAndAdd(
    list: List<GameRatingReview>,
): ImmutableList<RatingReviewListItem> =
    (this + list.map { RatingReviewListItem(it) }).toImmutableList()
