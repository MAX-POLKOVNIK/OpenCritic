package com.opencritic.api.dto.review

enum class ReviewSortKey(val queryValue: String) {
    Default(""),
    Popularity("popularity"),
    ScoreHigh("score-high"),
    ScoreLow("score-low"),
    Newest("newest"),
    Oldest("oldest"),
}