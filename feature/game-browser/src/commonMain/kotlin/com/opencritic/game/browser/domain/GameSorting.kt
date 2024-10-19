package com.opencritic.game.browser.domain

import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

enum class GameSorting {
    ReleaseDate,
    Score,
    ReviewsCount,
    PercentRecommended,
    AtoZ,
}

fun GameSorting.asTextSource(): TextSource {
    return when (this) {
        GameSorting.ReleaseDate -> StringRes.str_sort_release_date.asTextSource()
        GameSorting.Score -> StringRes.str_sort_score.asTextSource()
        GameSorting.ReviewsCount -> StringRes.str_sort_review_count.asTextSource()
        GameSorting.PercentRecommended -> StringRes.str_sort_percent_recommended.asTextSource()
        GameSorting.AtoZ -> StringRes.str_sort_a_to_z.asTextSource()
    }
}