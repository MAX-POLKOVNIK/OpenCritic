package com.opencritic.games.details.domain

import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

enum class ReviewSorting {
    Default,
    MostPopular,
    ScoreHighestToLowest,
    ScoreLowestToHighest,
    NewestFirst,
    OldestFirst,
}

fun ReviewSorting.asTextSource(): TextSource {
    return when (this) {
        ReviewSorting.Default -> StringRes.str_sort_default.asTextSource()
        ReviewSorting.MostPopular -> StringRes.str_sort_most_popular.asTextSource()
        ReviewSorting.ScoreHighestToLowest -> StringRes.str_sort_highest.asTextSource()
        ReviewSorting.ScoreLowestToHighest -> StringRes.str_sort_lowest.asTextSource()
        ReviewSorting.NewestFirst -> StringRes.str_sort_newest.asTextSource()
        ReviewSorting.OldestFirst -> StringRes.str_sort_oldest.asTextSource()
    }
}