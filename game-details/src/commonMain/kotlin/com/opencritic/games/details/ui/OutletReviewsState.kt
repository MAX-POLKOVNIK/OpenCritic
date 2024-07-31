package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.Icons
import com.opencritic.resources.ImageResourceProvider

interface OutletReviewsState : ViewModelState {
    val titleText: String
    data class Error(
        override val titleText: String,
        override val message: String
    ) : BaseErrorState(message), OutletReviewsState

    data class Loading(
        override val titleText: String,
    ) : BaseLoadingState(), OutletReviewsState

    data class Content(
        override val titleText: String,
        val outletNameText: String,
        val outletImageUrl: String,
        val isHomepageVisible: Boolean,
        val homepageText: String,
        val infoItems: List<IconTextItem>,
        val sortTitleText: String,
        val sortText: ReviewSortItem,
        val availableSorts: List<ReviewSortItem>,
        val reviewItems: List<ReviewListItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        val onLoadMore: () -> Unit,
        val onSelectedSort: (ReviewSortItem) -> Unit,
        val onHomepageClick: () -> Unit,
    ) : OutletReviewsState
}

@Suppress("FunctionName")
fun OutletReviewsStateContent_PreviewData(): OutletReviewsState.Content =
    OutletReviewsState.Content(
        titleText = "IGN's reviews",
        outletNameText = "IGN",
        outletImageUrl = "https://img.opencritic.com/outlet/56/1v75XFcW.jpg",
        isHomepageVisible = true,
        homepageText = "Homepage",
        sortTitleText = "Sort",
        infoItems = listOf(
            IconTextItem(
                icon = Icons.hashTag,
                text = "2188 games reviewed",
            ),
            IconTextItem(
                icon = Icons.chartPie,
                text = "74.4 average score",
            ),
            IconTextItem(
                icon = Icons.bullseye,
                text = "78 median score",
            ),
            IconTextItem(
                icon = Icons.thumbUp,
                text = "55.6% of games recommended"
            )
        ),
        sortText = ReviewSortItem(
            key = ReviewSorting.Default, name = ReviewSorting.Default.name
        ),
        availableSorts = ReviewSorting.entries
            .filter { it != ReviewSorting.MostPopular }
            .map { ReviewSortItem(it, it.name) },
        reviewItems = emptyList(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = { },
        onSelectedSort = { },
        onHomepageClick = { },
    )