package com.opencritic.games.details.domain

import com.opencritic.resources.StringProvider

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
        ReviewSorting.Default -> sortDefault
        ReviewSorting.MostPopular -> sortMostPopular
        ReviewSorting.ScoreHighestToLowest -> sortScoreHighestToLowest
        ReviewSorting.ScoreLowestToHighest -> sortScoreLowestToHighest
        ReviewSorting.NewestFirst -> sortNewestFirst
        ReviewSorting.OldestFirst -> sortOldestFirst
    }
}