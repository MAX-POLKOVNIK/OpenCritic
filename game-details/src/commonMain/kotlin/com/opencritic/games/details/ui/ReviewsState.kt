package com.opencritic.games.details.ui

import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider

interface ReviewsState : ViewModelState {
    data class Error(val message: String) : ReviewsState
    data object Loading : ReviewsState
    data class Content(
        val titleText: String,
        val imageUrl: String,
        val isTierVisible: Boolean,
        val tierImageResource: ImageResource,
        val isTopScoreIndicatorVisible: Boolean,
        val topScoreIndicator: RankCircleIndicatorItem,
        val isRecommendIndicatorVisible: Boolean,
        val recommendScoreIndicator: RankCircleIndicatorItem,
        val rankedDescription: String,
        val sortTitleText: String,
        val sortText: String,
        val availableSorts: List<String>,
        val reviewItems: List<ReviewListItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        private val onLoadMore: () -> Unit,
        private val onSelectedSort: (String) -> Unit,
    ) {
        fun selectedSort(sort: String) = onSelectedSort(sort)
        fun loadMore() = onLoadMore()
    }
}

@Suppress("FunctionName")
fun ReviewsStateContent_PreviewData(
    imageResourceProvider: ImageResourceProvider,
): ReviewsState.Content =
    ReviewsState.Content(
        titleText = "Stellar Blade Reviews",
        imageUrl = "https://img.opencritic.com/game/16510/Wc7Hwzgt.jpg",
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
        rankedDescription = "Stellar Blade is ranked in the 86th percentile of games scored on OpenCritic. ",
        sortTitleText = "Sort",
        sortText = "Default",
        availableSorts = listOf("Default", "Most Popular", "Score1", "Score2"),
        reviewItems = List(10) { ReviewListItem_PreviewData(it.toString()) },
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {},
        onSelectedSort = {},
    )