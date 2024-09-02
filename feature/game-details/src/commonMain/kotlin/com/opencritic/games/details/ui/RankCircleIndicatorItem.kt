package com.opencritic.games.details.ui

import androidx.annotation.FloatRange
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.roundScore
import com.opencritic.resources.colors.Color
import com.opencritic.resources.colors.Colors

data class RankCircleIndicatorItem(
    val scoreText: String,
    @FloatRange(0.0, 1.0)
    val progress: Float, // in range 0..1
    val colors: List<Color>,
    val backgroundColor: Color = Colors.CircleIndicatorBackground
)

fun createTopCriticAverageIndicator(gameRank: GameRank): RankCircleIndicatorItem =
    RankCircleIndicatorItem(
        scoreText = gameRank.score.toString(),
        progress = gameRank.score.toFloat() / 100f,
        colors = when (gameRank.tier) {
            Tier.Mighty -> Colors.MightyTopCriticGradient
            Tier.Strong -> Colors.StrongTopCriticGradient
            Tier.Fair -> Colors.FairTopCriticGradient
            Tier.Weak -> Colors.WeakTopCriticGradient
        }
    )

fun createCriticsRecommendIndicator(tier: Tier, score: Float): RankCircleIndicatorItem =
    RankCircleIndicatorItem(
        scoreText = "${score.roundScore()}%",
        progress = score.toFloat() / 100f,
        colors = when (tier) {
            Tier.Mighty -> Colors.MightyCriticsRecommendGradient
            Tier.Strong -> Colors.StrongCriticsRecommendGradient
            Tier.Fair -> Colors.FairCriticsRecommendGradient
            Tier.Weak -> Colors.WeakCriticsRecommendGradient
        }
    )