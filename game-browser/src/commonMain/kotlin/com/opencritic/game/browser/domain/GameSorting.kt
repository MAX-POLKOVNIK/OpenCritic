package com.opencritic.game.browser.domain

import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.resources.StringProvider

enum class GameSorting {
    ReleaseDate,
    Score,
    ReviewsCount,
    PercentRecommended,
    AtoZ,
}

fun StringProvider.sortNameOf(sort: GameSorting): String {
    return when(sort) {
        GameSorting.ReleaseDate -> sortReleaseDate
        GameSorting.Score -> sortScore
        GameSorting.ReviewsCount -> sortReviewsCount
        GameSorting.PercentRecommended -> sortPercentRecommended
        GameSorting.AtoZ -> sortAtoZ
    }
}