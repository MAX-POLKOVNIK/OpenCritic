package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class ReviewListItem(
    val id: String,
    val gameId: Long,
    val outletId: Int,
    val outletText: String,
    val isAuthorVisible: Boolean,
    val authorText: String,
    val authorId: Int?,
    val imageUrl: String,
    val isGameVisible: Boolean,
    val gameText: String,
    val score: ReviewScoreDisplayItem,
    val dateText: TextSource,
    val isSnippetVisible: Boolean,
    val snippetText: String,
    val isYoutubeVisible: Boolean,
    val youtubePlaceholderUrl: String?,
    val readFullReviewText: TextSource,
    val externalUrl: String,
    private val onClick: (ReviewListItem) -> Unit = ::emptyClickHandler,
    private val onImageClick: (ReviewListItem) -> Unit = ::emptyClickHandler,
    private val onGameClick: (ReviewListItem) -> Unit = ::emptyClickHandler,
    private val onAuthorClick: (ReviewListItem) -> Unit = ::emptyClickHandler,
    private val onOutletClick: (ReviewListItem) -> Unit = ::emptyClickHandler,
) {
    fun click() = onClick(this)
    fun imageClick() = onImageClick(this)
    fun authorClick() = onAuthorClick(this)
    fun outletClick() = onOutletClick(this)
    fun gameClick() = onGameClick(this)

    companion object {
        internal fun emptyClickHandler(@Suppress("UNUSED_PARAMETER") item: ReviewListItem) {}
    }
}

fun ReviewListItem(
    review: Review,
    isGameVisible: Boolean,
    onClick: (ReviewListItem) -> Unit = ReviewListItem.Companion::emptyClickHandler,
    onGameClick: (ReviewListItem) -> Unit = ReviewListItem.Companion::emptyClickHandler,
    onImageClick: (ReviewListItem) -> Unit = ReviewListItem.Companion::emptyClickHandler,
    onAuthorClick: (ReviewListItem) -> Unit = ReviewListItem.Companion::emptyClickHandler,
    onOutletClick: (ReviewListItem) -> Unit = ReviewListItem.Companion::emptyClickHandler,
): ReviewListItem =
    ReviewListItem(
        id = review.id,
        gameId = review.gameId,
        outletId = review.outlet.id,
        outletText = review.outlet.name,
        authorText = review.alias
            ?.takeIf { it.isNotBlank() }
            ?: review.authors.joinToString(", ") { it.name },
        isAuthorVisible = (review.alias
            ?.takeIf { it.isNotBlank() }
            ?: review.authors.joinToString(", ") { it.name }
            ).isNotBlank(),
        authorId = review.authors.firstOrNull()?.id,
        imageUrl = review.authors.map { it.imageUrl }.firstOrNull()
            ?: review.outlet.imageUrl,
        score = ReviewScoreDisplayItem(review.score, review.scoreFormat),
        dateText = review.publishedDate.toLocalDateTime(TimeZone.UTC).date format DateTextSource.Format.Medium,
        isSnippetVisible = review.snippet.isNotBlank(),
        snippetText = review.snippet,
        readFullReviewText = StringRes.str_read_full_review.asTextSource(),
        isGameVisible = isGameVisible,
        gameText = review.gameName,
        isYoutubeVisible = review.youtubePlaceholderUrl != null,
        youtubePlaceholderUrl = review.youtubePlaceholderUrl,
        externalUrl = review.externalUrl,
        onClick = onClick,
        onImageClick = onImageClick,
        onAuthorClick = onAuthorClick,
        onOutletClick = onOutletClick,
        onGameClick = onGameClick,
    )

internal fun List<ReviewListItem>.mapAndAdd(
    reviews: List<Review>,
    mapper: (Review) -> ReviewListItem,
): ImmutableList<ReviewListItem> =
    (this + reviews.map(mapper)).toImmutableList()

@Suppress("FunctionName")
fun ReviewListItem_PreviewData(id: String = "1"): ReviewListItem =
    ReviewListItem(
        id = id,
        gameId = 0,
        outletId = 0,
        isGameVisible = true,
        gameText = "Stellar Blade",
        outletText = "Digital Trends",
        isAuthorVisible = true,
        authorId = null,
        authorText = "Tomas Franzese",
        imageUrl = "https://img.opencritic.com/critic/2097/vh2JKJwl.jpg",
        score = ReviewScoreDisplayItem.Stars(
            filledStars = 3,
            halfStars = 1,
            emptyStars = 2,
        ),
        dateText = "May 1, 2024".asTextSource(),
        isSnippetVisible = true,
        snippetText = "Stellar Blade is a journey through the depths of human resilience and the cost of redemption, and stands as a testament to the power of storytelling in gaming. As players embark on Eve’s quest for truth and justice, they’ll find themselves immersed in a world where every choice carries weight, and the fate of humanity hangs in the balance.",
        readFullReviewText = "Read full review".asTextSource(),
        isYoutubeVisible = true,
        youtubePlaceholderUrl = "https://img.youtube.com/vi/OAEZNHFfeB4/maxresdefault.jpg",
        externalUrl = "https://img.youtube.com/vi/OAEZNHFfeB4/maxresdefault.jpg",
        onClick = ReviewListItem.Companion::emptyClickHandler,
        onGameClick = ReviewListItem.Companion::emptyClickHandler,
        onAuthorClick = ReviewListItem.Companion::emptyClickHandler,
        onImageClick = ReviewListItem.Companion::emptyClickHandler,
        onOutletClick = ReviewListItem.Companion::emptyClickHandler,
    )