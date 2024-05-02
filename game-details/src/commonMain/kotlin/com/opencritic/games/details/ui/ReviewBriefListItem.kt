package com.opencritic.games.details.ui

import com.opencritic.games.ReviewScoreFormat
import com.opencritic.mvvm.ListItem

data class ReviewBriefListItem(
    val nameText: String,
    val scoreText: String
) : ListItem<Int> {
    override val id: Int
        get() = hashCode()
}

fun ReviewBriefListItem(
    name: String,
    score: Int?,
    scoreFormat: ReviewScoreFormat
): ReviewBriefListItem =
    ReviewBriefListItem(
        nameText = name,
        scoreText =
            when {
                score == null -> "Unscored"
                scoreFormat.isSelect ->
                    scoreFormat.options
                        ?.filter { score > it.value }
                        ?.maxBy { it.value }
                        ?.label
                        ?: ""
                else ->
                    (score / requireNotNull(scoreFormat.base)).toString() +
                            " / ${ 100 / requireNotNull(scoreFormat.base)}"
            }
    )