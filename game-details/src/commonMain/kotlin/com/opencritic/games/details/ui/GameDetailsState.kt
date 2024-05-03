package com.opencritic.games.details.ui

import com.opencritic.games.Tier
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.ImageResource

sealed interface GameDetailsState : ViewModelState {
    data class Error(val message: String) : GameDetailsState
    data object Loading : GameDetailsState
    data class Content(
        val isImageVisible: Boolean,
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
        val topCriticScore: RankCircleIndicatorItem,
        val topCriticScoreDescription: String,
        val recommendedPercent: RankCircleIndicatorItem,
        val criticsRecommendDescription: String,
        val briefReviews: List<ReviewBriefListItem>,
        val isViewAllVisible: Boolean,
        val viewAllText: String,
        val isMediaVisible: Boolean,
        val mediaText: String,
        val media: List<MediaItem>,
        val viewAllMedia: String,
        private val onViewAllMediaClick: (GameDetailsState) -> Unit,
        val isTrailersVisible: Boolean,
        val trailersText: String,
        val trailers: List<TrailerItem>,
        val viewAllTrailers: String,
        private val onViewAllTrailersClick: (GameDetailsState) -> Unit,
        val isScreenshotsVisible: Boolean,
        val screenshotsText: String,
        val screenshots: List<ScreenshotItem>,
        val viewAllScreenshots: String,
        private val onViewAllScreenshotsClick: (GameDetailsState) -> Unit,
        val isReviewsVisible: Boolean,
        val reviewTitleText: String,
        val reviews: List<CardReviewItem>,
        private val onViewAllReviewsClick: () -> Unit,
    ) : GameDetailsState {
        fun viewAllMediaClick() = onViewAllMediaClick(this)
        fun viewAllTrailersClick() = onViewAllTrailersClick(this)
        fun viewAllScreenshotsClick() = onViewAllScreenshotsClick(this)
        fun viewAllReviewsClick() = onViewAllReviewsClick()
    }
}
