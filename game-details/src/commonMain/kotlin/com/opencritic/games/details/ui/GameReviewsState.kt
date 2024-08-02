package com.opencritic.games.details.ui

import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.images.SharedImageResource
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

interface GameReviewsState : ViewModelState {
    val titleText: TextSource

    data class Error(
        override val titleText: TextSource,
        override val message: TextSource
    ) : BaseErrorState(message), GameReviewsState

    data class Loading(
        override val titleText: TextSource
    ) : BaseLoadingState(), GameReviewsState

    data class Content(
        override val titleText: TextSource,
        val imageUrl: String,
        val isTierVisible: Boolean,
        val tierImageResource: SharedImageResource,
        val isTopScoreIndicatorVisible: Boolean,
        val topScoreIndicator: RankCircleIndicatorItem,
        val isRecommendIndicatorVisible: Boolean,
        val recommendScoreIndicator: RankCircleIndicatorItem,
        val isRankedDescriptionVisible: Boolean,
        val rankedDescription: TextSource,
        val sortTitleText: TextSource,
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
fun GameReviewsStateContent_PreviewData(): GameReviewsState.Content =
    GameReviewsState.Content(
        titleText = "Stellar Blade Reviews".asTextSource(),
        imageUrl = "https://img.opencritic.com/game/14343/ZpFE5hYe.jpg",
        isTierVisible = true,
        tierImageResource = SharedImages.strongMan,
        isTopScoreIndicatorVisible = true,
        topScoreIndicator = createTopCriticAverageIndicator(
            GameRank(Tier.Strong, 82)
        ),
        isRecommendIndicatorVisible = true,
        recommendScoreIndicator = createCriticsRecommendIndicator(
            tier = Tier.Strong,
            score = 85f
        ),
        isRankedDescriptionVisible = true,
        rankedDescription = "Stellar Blade is ranked in the 86th percentile of games scored on OpenCritic. ".asTextSource(),
        sortTitleText = "Sort".asTextSource(),
        sortText = ReviewSortItem(ReviewSorting.Default, ReviewSorting.Default.asTextSource()),
        availableSorts = ReviewSorting.entries.map { ReviewSortItem(it, it.asTextSource()) },
        reviewItems = List(10) { ReviewListItem_PreviewData(it.toString()) },
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
        onSelectedSort = {},
    )