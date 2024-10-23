package com.opencritic.api.dto.rating

enum class GameRatingReviewTimeFrameKey(val key: String) {
    AllTime(""),
    Today("today"),
    Week("week"),
    Month("month"),
    Year("year")
}