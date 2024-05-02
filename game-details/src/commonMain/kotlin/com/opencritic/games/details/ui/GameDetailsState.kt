package com.opencritic.games.details.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.ImageResource
import com.opencritic.resources.StringResource

sealed interface GameDetailsState : ViewModelState {
    data class Error(val message: String) : GameDetailsState
    data object Loading : GameDetailsState
    data class Content(
        val imageUrl: String,
        val name: String,
        val gameActionItems: List<GameActionItem>,
        val companiesText: String,
        val releaseDateText: String,
        val platformsText: String,
        val isTierVisible: Boolean,
        val tier: Tier?,
        val tierImageResource: ImageResource,
        val tierDescription: String,
        val topCriticScore: Int?,
        val topCriticScoreDescription: String,
        val recommendedPercent: Int?,
        val criticsRecommendDescription: String,
        val briefReviews: List<ReviewBriefListItem>,
        val viewAllText: String,
    ) : GameDetailsState
}