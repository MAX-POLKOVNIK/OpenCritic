package com.opencritic.games.details.ui

import com.opencritic.game.your.ui.YourGameIndicatorItem
import com.opencritic.game.your.ui.YourGameIndicatorItem_PreviewData
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.images.SharedImageResource
import com.opencritic.resources.images.SharedImages

sealed interface GameDetailsState : ViewModelState {
    val titleText: String

    data class Error(override val titleText: String, override val message: String) :
        BaseErrorState(message),
        GameDetailsState

    data class Loading(override val titleText: String) :
        BaseLoadingState(),
        GameDetailsState

    data class Content(
        override val titleText: String,
        val isImageVisible: Boolean,
        val imageUrl: String,
        val name: String,
        val yourGameIndicatorItem: YourGameIndicatorItem,
        val companiesText: String,
        val releaseDateText: TextSource,
        val platformsText: String,
        val isTierVisible: Boolean,
        val tier: Tier?,
        val tierImageResource: SharedImageResource,
        val tierDescription: TextSource,
        val topCriticScore: RankCircleIndicatorItem,
        val topCriticScoreDescription: TextSource,
        val recommendedPercent: RankCircleIndicatorItem,
        val criticsRecommendDescription: TextSource,
        val briefReviews: List<ReviewBriefListItem>,
        val isViewAllVisible: Boolean,
        val viewAllText: TextSource,
        val isMediaVisible: Boolean,
        val mediaText: TextSource,
        val media: List<MediaItem>,
        val viewAllMedia: TextSource,
        private val onViewAllMediaClick: (GameDetailsState) -> Unit,
        val isTrailersVisible: Boolean,
        val trailersText: TextSource,
        val trailers: List<TrailerItem>,
        val viewAllTrailers: TextSource,
        private val onViewAllTrailersClick: (GameDetailsState) -> Unit,
        val isScreenshotsVisible: Boolean,
        val screenshotsText: TextSource,
        val screenshots: List<ScreenshotItem>,
        val viewAllScreenshots: TextSource,
        private val onViewAllScreenshotsClick: (GameDetailsState) -> Unit,
        val isReviewsVisible: Boolean,
        val reviewTitleText: TextSource,
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
fun GameDetailsStateContent_PreviewData(): GameDetailsState.Content =
    GameDetailsState.Content(
        isImageVisible = true,
        imageUrl = "https://img.opencritic.com/game/14353/a7GST4so.jpg",
        name = "Game title",
        yourGameIndicatorItem = YourGameIndicatorItem_PreviewData(),
        companiesText = "Some companies",
        releaseDateText = "MAY 25, 2505".asTextSource(),
        platformsText = "Playstation, Xbox, PC",
        isTierVisible = true,
        tier = Tier.Fair,
        tierDescription = "Tier description".asTextSource(),
        tierImageResource = SharedImages.fairMan,
        topCriticScore = createTopCriticAverageIndicator(GameRank(Tier.Fair, 20)),
        topCriticScoreDescription = "Top critic description".asTextSource(),
        recommendedPercent = createCriticsRecommendIndicator(Tier.Fair, 40),
        criticsRecommendDescription = "Critics recommends".asTextSource(),
        briefReviews = listOf(
            ReviewBriefListItem(nameText = "IGN", scoreText = "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
        ),
        isViewAllVisible = true,
        viewAllText = "View all 1000 reviews".asTextSource(),
        isMediaVisible = false,
        mediaText = "".asTextSource(),
        media = emptyList(),
        viewAllMedia = "".asTextSource(),
        onViewAllMediaClick = {},
        isTrailersVisible = false,
        trailersText = "".asTextSource(),
        trailers = emptyList(),
        viewAllTrailers = "".asTextSource(),
        onViewAllTrailersClick = {},
        isScreenshotsVisible = false,
        screenshotsText = "".asTextSource(),
        screenshots = emptyList(),
        viewAllScreenshots = "".asTextSource(),
        onViewAllScreenshotsClick = {},
        isReviewsVisible = false,
        reviewTitleText = "".asTextSource(),
        reviews = emptyList(),
        onViewAllReviewsClick = {},
        titleText = "Game",
    )
