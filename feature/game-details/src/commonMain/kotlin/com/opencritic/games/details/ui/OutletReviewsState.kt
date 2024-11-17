package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

interface OutletReviewsState : ViewModelState {
    val titleText: TextSource
    data class Error(
        override val titleText: TextSource,
        override val message: TextSource
    ) : BaseErrorState(message), OutletReviewsState

    data class Loading(
        override val titleText: TextSource,
    ) : BaseLoadingState(), OutletReviewsState

    data class Content(
        override val titleText: TextSource,
        val outletNameText: String,
        val outletImageUrl: String,
        val isHomepageVisible: Boolean,
        val homepageText: TextSource,
        val infoItems: ImmutableList<IconTextItem>,
        val sortTitleText: TextSource,
        val sortText: ReviewSortItem,
        val availableSorts: ImmutableList<ReviewSortItem>,
        val reviewItems: ImmutableList<ReviewListItem>,
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
        titleText = "IGN's reviews".asTextSource(),
        outletNameText = "IGN",
        outletImageUrl = "https://img.opencritic.com/outlet/56/1v75XFcW.jpg",
        isHomepageVisible = true,
        homepageText = "Homepage".asTextSource(),
        sortTitleText = "Sort".asTextSource(),
        infoItems = persistentListOf(
            IconTextItem(
                icon = Icons.hashTag,
                text = "2188 games reviewed".asTextSource(),
            ),
            IconTextItem(
                icon = Icons.chartPie,
                text = "74.4 average score".asTextSource(),
            ),
            IconTextItem(
                icon = Icons.bullseye,
                text = "78 median score".asTextSource(),
            ),
            IconTextItem(
                icon = Icons.thumbUp,
                text = "55.6% of games recommended".asTextSource()
            )
        ),
        sortText = ReviewSortItem(
            key = ReviewSorting.Default, name = ReviewSorting.Default.asTextSource()
        ),
        availableSorts = ReviewSorting.entries
            .filter { it != ReviewSorting.MostPopular }
            .map { ReviewSortItem(it, it.asTextSource()) }
            .toImmutableList(),
        reviewItems = persistentListOf(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = { },
        onSelectedSort = { },
        onHomepageClick = { },
    )