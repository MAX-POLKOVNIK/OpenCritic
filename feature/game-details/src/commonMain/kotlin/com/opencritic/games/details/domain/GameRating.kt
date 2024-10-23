package com.opencritic.games.details.domain

data class GameRating(
    val median: Int?,
    val count: Int,
) {
    val isCalculated: Boolean = count >= minCount

    companion object {
        const val minCount = 20
    }
}