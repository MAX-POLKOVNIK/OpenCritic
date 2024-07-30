package com.opencritic.games.details.domain

import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getString

enum class ReviewSorting {
    Default,
    MostPopular,
    ScoreHighestToLowest,
    ScoreLowestToHighest,
    NewestFirst,
    OldestFirst,
}

fun StringProvider.sortNameOf(sort: ReviewSorting): String {
    return when(sort) {
        ReviewSorting.Default -> getString(StringRes.str_sort_default)
        ReviewSorting.MostPopular -> getString(StringRes.str_sort_most_popular)
        ReviewSorting.ScoreHighestToLowest -> getString(StringRes.str_sort_highest)
        ReviewSorting.ScoreLowestToHighest -> getString(StringRes.str_sort_lowest)
        ReviewSorting.NewestFirst -> getString(StringRes.str_sort_newest)
        ReviewSorting.OldestFirst -> getString(StringRes.str_sort_oldest)
    }
}