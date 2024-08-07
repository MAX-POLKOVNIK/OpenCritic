package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class ReviewListItem(
    val id: String,
    val outletText: String,
    val isAuthorVisible: Boolean,
    val authorText: String,
    val imageUrl: String,
    val isGameVisible: Boolean,
    val gameText: String,
    val score: ReviewScoreDisplayItem,
    val dateText: TextSource,
    val isSnipperVisible: Boolean,
    val snippetText: String,
    val isYoutubeVisible: Boolean,
    val youtubePlaceholderUrl: String?,
    val readFullReviewText: TextSource,
    private val onClick: (ReviewListItem) -> Unit,
    private val onImageClick: (ReviewListItem) -> Unit,
    private val onGameClick: (ReviewListItem) -> Unit,
    private val onAuthorClick: (ReviewListItem) -> Unit,
    private val onOutletClick: (ReviewListItem) -> Unit,
) {
    fun click() = onClick(this)
    fun imageClick() = onImageClick(this)
    fun authorClick() = onAuthorClick(this)
    fun outletClick() = onOutletClick(this)
    fun gameClick() = onGameClick(this)
}

fun ReviewListItem(
    review: Review,
    isGameVisible: Boolean,
    onClick: (ReviewListItem) -> Unit,
    onGameClick: (ReviewListItem) -> Unit,
    onImageClick: (ReviewListItem) -> Unit,
    onAuthorClick: (ReviewListItem) -> Unit,
    onOutletClick: (ReviewListItem) -> Unit,
): ReviewListItem =
    ReviewListItem(
        id = review.id,
        outletText = review.outlet.name,
        authorText = review.alias
            ?.takeIf { it.isNotBlank() }
            ?: review.authors.joinToString(", ") { it.name },
        isAuthorVisible = (review.alias
            ?.takeIf { it.isNotBlank() }
            ?: review.authors.joinToString(", ") { it.name }
                ).isNotBlank(),
        imageUrl = review.authors.map { it.imageUrl }.firstOrNull()
            ?: review.outlet.imageUrl,
        score = ReviewScoreDisplayItem(review.score, review.scoreFormat),
        dateText = review.publishedDate.toLocalDateTime(TimeZone.UTC).date format DateTextSource.Format.Medium,
        isSnipperVisible = review.snippet.isNotBlank(),
        snippetText = review.snippet,
        readFullReviewText = StringRes.str_read_full_review.asTextSource(),
        isGameVisible = isGameVisible,
        gameText = review.gameName,
        isYoutubeVisible = review.youtubePlaceholderUrl != null,
        youtubePlaceholderUrl = review.youtubePlaceholderUrl,
        onClick = onClick,
        onImageClick = onImageClick,
        onAuthorClick = onAuthorClick,
        onOutletClick = onOutletClick,
        onGameClick = onGameClick,
    )


@Suppress("FunctionName")
fun ReviewListItem_PreviewData(id: String = "1"): ReviewListItem =
    ReviewListItem(
        id = id,
        isGameVisible = true,
        gameText = "Stellar Blade",
        outletText = "Digital Trends",
        isAuthorVisible = true,
        authorText = "Tomas Franzese",
        imageUrl = "https://img.opencritic.com/critic/2097/vh2JKJwl.jpg",
        score = ReviewScoreDisplayItem.Stars(
            filledStars = 3,
            halfStars = 1,
            emptyStars = 2,
        ),
        dateText = "May 1, 2024".asTextSource(),
        isSnipperVisible = true,
        snippetText = "Stellar Blade is a journey through the depths of human resilience and the cost of redemption, and stands as a testament to the power of storytelling in gaming. As players embark on Eve’s quest for truth and justice, they’ll find themselves immersed in a world where every choice carries weight, and the fate of humanity hangs in the balance.",
        readFullReviewText = "Read full review".asTextSource(),
        isYoutubeVisible = true,
        youtubePlaceholderUrl = "https://img.youtube.com/vi/OAEZNHFfeB4/maxresdefault.jpg",
        onClick = {},
        onGameClick = {},
        onAuthorClick = {},
        onImageClick = {},
        onOutletClick = {},
    )