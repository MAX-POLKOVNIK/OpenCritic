package com.opencritic.games.details.ui.reviews.rating

import com.opencritic.games.details.domain.RatingTimeframe
import com.opencritic.resources.text.TextSource

data class RatingReviewTimeframeItem(
    val key: RatingTimeframe,
    val text: TextSource,
)
