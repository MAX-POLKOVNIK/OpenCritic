package com.opencritic.games.details.ui

import com.opencritic.game.your.ui.lists.YourGameIndicatorItem
import com.opencritic.game.your.ui.lists.YourGameIndicatorItem_PreviewData
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.domain.GameRating
import com.opencritic.mvvm.ActionedScreenContent
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.SharedImageResource
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class GameDetailsContent(
    val squareImageUrl: String,
    val bannerImageUrl: String,
    val name: String,
    val yourGameIndicatorItem: YourGameIndicatorItem,
    val creatorsText: TextSource,
    val releaseText: TextSource,
    val platformsText: TextSource,
    val isTierVisible: Boolean,
    val tier: Tier?,
    val tierImageResource: SharedImageResource,
    val tierDescription: TextSource,
    val topCriticScore: RankCircleIndicatorItem,
    val topCriticScoreDescription: TextSource,
    val recommendedPercent: RankCircleIndicatorItem,
    val criticsRecommendDescription: TextSource,
    val briefReviews: ImmutableList<ReviewBriefListItem>,
    val playerRating: RankCircleIndicatorItem,
    val playerRatingDescription: TextSource,
    val isViewAllVisible: Boolean,
    val viewAllText: TextSource,
    val isMediaVisible: Boolean,
    val mediaText: TextSource,
    val media: ImmutableList<MediaItem>,
    val viewAllMedia: TextSource,
    val onViewAllMediaClick: () -> Unit,
    val isTrailersVisible: Boolean,
    val trailersText: TextSource,
    val trailers: ImmutableList<TrailerItem>,
    val viewAllTrailers: TextSource,
    val onViewAllTrailersClick: () -> Unit,
    val isScreenshotsVisible: Boolean,
    val screenshotsText: TextSource,
    val screenshots: ImmutableList<ScreenshotItem>,
    val viewAllScreenshots: TextSource,
    val onViewAllScreenshotsClick: () -> Unit,
    val isReviewsVisible: Boolean,
    val reviewTitleText: TextSource,
    val reviews: ImmutableList<CardReviewItem>,
    val onViewAllReviewsClick: () -> Unit,
    val onRefresh: () -> Unit,
    val onGameRatingClick: () -> Unit,
    override val isActionVisible: Boolean,
    override val actionIconResource: IconResource,
    override val onAction: () -> Unit,
) : ActionedScreenContent

@Suppress("FunctionName")
fun GameDetailsContent_PreviewData(): GameDetailsContent =
    GameDetailsContent(
        squareImageUrl = "https://img.opencritic.com/game/14353/a7GST4so.jpg",
        bannerImageUrl = "https://img.opencritic.com/game/16948/8Uwqfbbn.jpg",
        name = "Game title",
        yourGameIndicatorItem = YourGameIndicatorItem_PreviewData(),
        creatorsText = "Some companies".asTextSource(),
        releaseText = "MAY 25, 2505".asTextSource(),
        platformsText = "PC".asTextSource(),
        isTierVisible = true,
        tier = Tier.Fair,
        tierDescription = "Tier description".asTextSource(),
        tierImageResource = SharedImages.fairMan,
        topCriticScore = createTopCriticAverageIndicator(GameRank(Tier.Fair, 20)),
        topCriticScoreDescription = "Top critic description".asTextSource(),
        recommendedPercent = createCriticsRecommendIndicator(Tier.Fair, 40f),
        criticsRecommendDescription = "Critics recommends".asTextSource(),
        briefReviews = persistentListOf(
            ReviewBriefListItem(nameText = "IGN", scoreText = "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
            ReviewBriefListItem(nameText = "IGN", scoreText =  "100 / 100"),
        ),
        playerRating = createPlayerRatingIndicator(Tier.Fair, GameRating(100, 100)),
        playerRatingDescription = "Player Rating".asTextSource(),
        isViewAllVisible = true,
        viewAllText = "View all 1000 reviews".asTextSource(),
        isMediaVisible = false,
        mediaText = "".asTextSource(),
        media = persistentListOf(),
        viewAllMedia = "".asTextSource(),
        onViewAllMediaClick = {},
        isTrailersVisible = false,
        trailersText = "".asTextSource(),
        trailers = persistentListOf(),
        viewAllTrailers = "".asTextSource(),
        onViewAllTrailersClick = {},
        isScreenshotsVisible = false,
        screenshotsText = "".asTextSource(),
        screenshots = persistentListOf(),
        viewAllScreenshots = "".asTextSource(),
        onViewAllScreenshotsClick = {},
        isReviewsVisible = false,
        reviewTitleText = "".asTextSource(),
        reviews = persistentListOf(),
        onViewAllReviewsClick = {},
        onRefresh = {},
        onGameRatingClick = {},
        isActionVisible = true,
        actionIconResource = Icons.share,
        onAction = {}
    )