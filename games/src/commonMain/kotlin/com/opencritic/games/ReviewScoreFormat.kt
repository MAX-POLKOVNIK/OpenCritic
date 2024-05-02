package com.opencritic.games

data class ReviewScoreFormat(
    val id: Int,
    val name: String,
    val shortName: String,
    val scoreDisplay: String?,
    val isNumeric: Boolean,
    val isSelect: Boolean,
    val isStars: Boolean,
    val isPercent: Boolean,
    val numDecimals: Int?,
    val base: Int?,
    val options: List<ReviewScoreFormatOption>?,
)