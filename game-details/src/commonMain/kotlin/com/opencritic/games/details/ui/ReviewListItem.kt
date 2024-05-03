package com.opencritic.games.details.ui

data class ReviewListItem(
    val id: String,
    val outletText: String,
    val isAuthorVisible: Boolean,
    val authorText: String,
    val imageUrl: String,
    val score: ReviewScoreDisplayItem,
    val dateText: String,
    val snippetText: String,
    val readFullReviewText: String,
    private val onClick: (ReviewListItem) -> Unit,
    private val onImageClick: (ReviewListItem) -> Unit,
    private val onAuthorClick: (ReviewListItem) -> Unit,
    private val onOutletClick: (ReviewListItem) -> Unit,
) {
    fun click() = onClick(this)
    fun imageClick() = onImageClick(this)
    fun authorClick() = onAuthorClick(this)
    fun outletClick() = onOutletClick(this)
}

@Suppress("FunctionName")
fun ReviewListItem_PreviewData(id: String = "1"): ReviewListItem =
    ReviewListItem(
        id = id,
        outletText = "Digital Trends",
        isAuthorVisible = true,
        authorText = "Tomas Franzese",
        imageUrl = "https://img.opencritic.com/critic/2097/vh2JKJwl.jpg",
        score = ReviewScoreDisplayItem.Stars(
            filledStars = 3,
            halfStars = 0,
            emptyStars = 2,
        ),
        dateText = "May 1, 2024",
        snippetText = "Stellar Blade is a journey through the depths of human resilience and the cost of redemption, and stands as a testament to the power of storytelling in gaming. As players embark on Eve’s quest for truth and justice, they’ll find themselves immersed in a world where every choice carries weight, and the fate of humanity hangs in the balance.",
        readFullReviewText = "Read full review",
        onClick = {},
        onAuthorClick = {},
        onImageClick = {},
        onOutletClick = {},
    )