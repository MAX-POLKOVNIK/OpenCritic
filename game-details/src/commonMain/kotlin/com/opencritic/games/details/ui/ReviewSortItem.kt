package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.resources.text.TextSource

data class ReviewSortItem(
    val key: ReviewSorting,
    val name: TextSource,
)