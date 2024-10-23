package com.opencritic.games.details.ui.reviews.rating

import com.opencritic.games.details.domain.RatingReviewSorting
import com.opencritic.resources.text.TextSource

data class RatingReviewSortItem(
    val key: RatingReviewSorting,
    val text: TextSource,
)
