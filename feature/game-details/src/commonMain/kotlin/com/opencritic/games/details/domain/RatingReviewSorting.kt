package com.opencritic.games.details.domain

import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

enum class RatingReviewSorting {
    Newest,
    Score,
}

fun RatingReviewSorting.asTextSource(): TextSource {
    return when (this) {
        RatingReviewSorting.Newest -> StringRes.str_rating_reviews_sort_newest
        RatingReviewSorting.Score -> StringRes.str_rating_reviews_sort_score
    }.asTextSource()
}