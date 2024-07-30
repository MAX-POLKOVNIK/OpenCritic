package com.opencritic.game.browser.domain

import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getString

enum class GameSorting {
    ReleaseDate,
    Score,
    ReviewsCount,
    PercentRecommended,
    AtoZ,
}

fun StringProvider.sortNameOf(sort: GameSorting): String {
    return when(sort) {
        GameSorting.ReleaseDate -> getString(StringRes.str_sort_release_date)
        GameSorting.Score -> getString(StringRes.str_sort_score)
        GameSorting.ReviewsCount -> getString(StringRes.str_sort_review_count)
        GameSorting.PercentRecommended -> getString(StringRes.str_sort_percent_recommended)
        GameSorting.AtoZ -> getString(StringRes.str_sort_a_to_z)
    }
}