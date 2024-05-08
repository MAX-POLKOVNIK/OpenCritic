package com.opencritic.games.details.ui

import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider

interface GameReviewsState : ViewModelState {
    val titleText: String

    data class Error(
        override val titleText: String,
        override val message: String
    ) : BaseErrorState(message), GameReviewsState

    data class Loading(
        override val titleText: String
    ) : BaseLoadingState(), GameReviewsState

    data class Content(
        override val titleText: String,
        val imageUrl: String,
        val isTierVisible: Boolean,
        val tierImageResource: ImageResource,
        val isTopScoreIndicatorVisible: Boolean,
        val topScoreIndicator: RankCircleIndicatorItem,
        val isRecommendIndicatorVisible: Boolean,
        val recommendScoreIndicator: RankCircleIndicatorItem,
        val isRankedDescriptionVisible: Boolean,
        val rankedDescription: String,
        val sortTitleText: String,
        val sortText: ReviewSortItem,
        val availableSorts: List<ReviewSortItem>,
        val reviewItems: List<ReviewListItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        private val onLoadMore: () -> Unit,
        private val onSelectedSort: (ReviewSortItem) -> Unit,
    ) : GameReviewsState {
        fun selectedSort(sort: ReviewSortItem) = onSelectedSort(sort)
        fun loadMore() = onLoadMore()
    }
}

@Suppress("FunctionName")
fun GameReviewsStateContent_PreviewData(
    imageResourceProvider: ImageResourceProvider,
): GameReviewsState.Content =
    GameReviewsState.Content(
        titleText = "Stellar Blade Reviews",
        imageUrl = "https://img.opencritic.com/game/14343/ZpFE5hYe.jpg",
        isTierVisible = true,
        tierImageResource = imageResourceProvider.strongMan,
        isTopScoreIndicatorVisible = true,
        topScoreIndicator = createTopCriticAverageIndicator(
            GameRank(Tier.Strong, 82)
        ),
        isRecommendIndicatorVisible = true,
        recommendScoreIndicator = createCriticsRecommendIndicator(
            Tier.Strong, 85
        ),
        isRankedDescriptionVisible = true,
        rankedDescription = "Stellar Blade is ranked in the 86th percentile of games scored on OpenCritic. ",
        sortTitleText = "Sort",
        sortText = ReviewSortItem(ReviewSorting.Default, ReviewSorting.Default.name),
        availableSorts = ReviewSorting.entries.map { ReviewSortItem(it, it.name) },
        reviewItems = List(10) { ReviewListItem_PreviewData(it.toString()) },
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
        onSelectedSort = {},
    )