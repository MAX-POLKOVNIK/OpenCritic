package com.opencritic.games

import kotlin.math.round

data class GameRank(
    val tier: Tier,
    val score: Int,
)

fun GameRank(tier: String, score: Float): GameRank? =
    if (tier.isBlank() || score < 0) null
    else GameRank(
        tier = Tier(tier),
        score = score.roundScore(),
    )

fun Float.roundScore(): Int =
    round(this).toInt()