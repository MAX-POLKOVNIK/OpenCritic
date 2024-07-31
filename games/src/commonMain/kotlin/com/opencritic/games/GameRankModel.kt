package com.opencritic.games

import com.opencritic.games.Tier.*
import com.opencritic.resources.images.SharedImageResource
import com.opencritic.resources.images.SharedImages

data class GameRankModel(
    val headImageResource: SharedImageResource,
    val scoreText: String,
)

fun GameRankModel(
    gameRank: GameRank?,
): GameRankModel? =
    gameRank?.let {
        GameRankModel(
            headImageResource = when (it.tier) {
                Weak -> SharedImages.weakHead
                Fair -> SharedImages.fairHead
                Strong -> SharedImages.strongHead
                Mighty -> SharedImages.mightyHead
            },
            scoreText = it.score.toString()
        )
    }
