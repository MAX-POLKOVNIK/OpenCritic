package com.opencritic.api.dto.game

enum class GameSortKey(val queryValue: String) {
    Score("score"),
    Date("date"),
    Name("name"),
    NumReviews("num-reviews"),
    PercentRecommended("percent-recommended")
}