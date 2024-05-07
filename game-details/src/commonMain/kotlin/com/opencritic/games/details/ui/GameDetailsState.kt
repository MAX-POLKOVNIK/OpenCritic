package com.opencritic.games.details.ui

import com.opencritic.game.your.ui.YourGameIndicatorItem
import com.opencritic.game.your.ui.YourGameIndicatorItem_PreviewData
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider

sealed interface GameDetailsState : ViewModelState {
    data class Error(val message: String) : GameDetailsState
    data object Loading : GameDetailsState
    data class Content(
        val isImageVisible: Boolean,
        val imageUrl: String,
        val name: String,
        val yourGameIndicatorItem: YourGameIndicatorItem,
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


@Suppress("FunctionName")
fun GameDetailsStateContent_PreviewData(
    imageResourceProvider: ImageResourceProvider
): GameDetailsState.Content =
    GameDetailsState.Content(
        isImageVisible = true,
        imageUrl = "https://img.opencritic.com/game/14353/a7GST4so.jpg",
        name = "Game title",
        yourGameIndicatorItem = YourGameIndicatorItem_PreviewData(imageResourceProvider),
        companiesText = "Some companies",
        releaseDateText = "MAY 25, 2505",
        platformsText = "Playstation, Xbox, PC",
        isTierVisible = true,
        tier = Tier.Fair,
        tierDescription = "Tier description",
        tierImageResource = imageResourceProvider.fairMan,
        topCriticScore = createTopCriticAverageIndicator(GameRank(Tier.Fair, 20)),
        topCriticScoreDescription = "Top critic description",
        recommendedPercent = createCriticsRecommendIndicator(Tier.Fair, 40),
        criticsRecommendDescription = "Critics recommends",
        briefReviews = listOf(
            ReviewBriefListItem(nameText = "IGN", scoreText = "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
        ),
        isViewAllVisible = true,
        viewAllText = "View all 1000 reviews",
        isMediaVisible = false,
        mediaText = "",
        media = emptyList(),
        viewAllMedia = "",
        onViewAllMediaClick = {},
        isTrailersVisible = false,
        trailersText = "",
        trailers = emptyList(),
        viewAllTrailers = "",
        onViewAllTrailersClick = {},
        isScreenshotsVisible = false,
        screenshotsText = "",
        screenshots = emptyList(),
        viewAllScreenshots = "",
        onViewAllScreenshotsClick = {},
        isReviewsVisible = false,
        reviewTitleText = "",
        reviews = emptyList(),
        onViewAllReviewsClick = {}
    )
