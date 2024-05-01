package com.opencritic.games

data class GameRank(
    val tier: Tier,
    val score: Int,
)

fun GameRank(tier: String, score: Float): GameRank? =
    if (tier.isBlank() || score < 0) null
    else GameRank(
        tier = Tier(tier),
        score = score.toInt(),
    )