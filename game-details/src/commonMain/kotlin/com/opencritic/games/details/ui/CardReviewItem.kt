package com.opencritic.games.details.ui

import com.opencritic.games.Review

data class CardReviewItem(
    val id: String,
    val outletText: String,
    val authorText: String,
    val outletThumbnailUrl: String,
    val score: ReviewScoreDisplayItem,
    val snippetText: String,
    val readFullReviewText: String,
    private val onClick: (CardReviewItem) -> Unit,
) {
    fun click() = onClick(this)
}

fun CardReviewItem(
    review: Review,
    readFullReviewText: String,
    onClick: (CardReviewItem) -> Unit,
): CardReviewItem =
    CardReviewItem(
        id = review.id,
        outletText = review.outlet.name,
        authorText = review.alias ?: review.authors.joinToString(", ") { it.name },
        outletThumbnailUrl = review.outlet.imageUrl,
        score = ReviewScoreDisplayItem(review.score, review.scoreFormat),
        snippetText = review.snippet,
        readFullReviewText = readFullReviewText,
        onClick = onClick,
    )