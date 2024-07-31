package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.resources.text.TextSource

data class CardReviewItem(
    val id: String,
    val outletText: String,
    val authorText: String,
    val outletThumbnailUrl: String,
    val score: ReviewScoreDisplayItem,
    val snippetText: String,
    val readFullReviewText: TextSource,
    private val onClick: (CardReviewItem) -> Unit,
) {
    fun click() = onClick(this)
}

fun CardReviewItem(
    review: Review,
    readFullReviewText: TextSource,
    onClick: (CardReviewItem) -> Unit,
): CardReviewItem =
    CardReviewItem(
        id = review.id,
        outletText = review.outlet.name,
        authorText = review.alias
            ?.takeIf { it.isNotBlank() }
            ?: review.authors.joinToString(", ") { it.name },
        outletThumbnailUrl = review.outlet.imageUrl,
        score = ReviewScoreDisplayItem(review.score, review.scoreFormat),
        snippetText = review.snippet,
        readFullReviewText = readFullReviewText,
        onClick = onClick,
    )