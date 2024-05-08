package com.opencritic.games.details.ui

import com.opencritic.games.ReviewScoreFormat

sealed interface ReviewScoreDisplayItem {
    data class String(val value: kotlin.String) : ReviewScoreDisplayItem
    data class Stars(
        val filledStars: Int,
        val halfStars: Int,
        val emptyStars: Int,
    ) : ReviewScoreDisplayItem {
        constructor(score: Float, totalStars: Int) : this(
            filledStars = score.toInt(),
            halfStars =
                if (score.toInt().toFloat() == score) 0
                else 1,
            emptyStars = totalStars - score.toInt() -
                    if (score.toInt().toFloat() == score) 0
                    else 1,
        )
    }
}

fun ReviewScoreDisplayItem(
    score: Float?,
    scoreFormat: ReviewScoreFormat,
): ReviewScoreDisplayItem =
    when {
        score == null -> ReviewScoreDisplayItem.String(value = "Unscored")
        scoreFormat.isSelect ->
            ReviewScoreDisplayItem.String(
                value = scoreFormat.options
                    ?.filter { score >= it.value }
                    ?.maxBy { it.value }
                    ?.label
                    ?: ""
            )
        scoreFormat.isStars ->
            ReviewScoreDisplayItem.Stars(
                score = score / requireNotNull(scoreFormat.base) { "Hasn't base in format: ${scoreFormat.id}" },
                totalStars = 100 / requireNotNull(scoreFormat.base) { "Hasn't base in format: ${scoreFormat.id}" },
            )
        scoreFormat.base == null ->
            ReviewScoreDisplayItem.String(scoreFormat.shortName)
        else ->
            ReviewScoreDisplayItem.String(
                value = (score.toFloat() / requireNotNull(scoreFormat.base) { "Hasn't base in format: ${scoreFormat.id}" }).formatScore() +
                        if (scoreFormat.isPercent) "%"
                        else " / ${ 100 / requireNotNull(scoreFormat.base) { "Hasn't base in format: ${scoreFormat.id}" }}"
            )
    }