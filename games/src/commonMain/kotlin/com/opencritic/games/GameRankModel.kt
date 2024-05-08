package com.opencritic.games

import com.opencritic.games.Tier.*
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider

data class GameRankModel(
    val headImageResource: ImageResource,
    val scoreText: String,
)

fun GameRankModel(
    imageResourceProvider: ImageResourceProvider,
    gameRank: GameRank?,
): GameRankModel? =
    gameRank?.let {
        GameRankModel(
            headImageResource = when (it.tier) {
                Weak -> imageResourceProvider.weakHead
                Fair -> imageResourceProvider.fairHead
                Strong -> imageResourceProvider.strongHead
                Mighty -> imageResourceProvider.mightyHead
            },
            scoreText = it.score.toString()
        )
    }
