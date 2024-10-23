package com.opencritic.games.details.ui.reviews.rating

import com.opencritic.games.details.domain.RatingReviewSorting
import com.opencritic.games.details.domain.RatingTimeframe
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface RatingReviewsState : ViewModelState {
    val titleText: TextSource

    data class Error(
        override val titleText: TextSource,
        override val message: TextSource
    ) : BaseErrorState(message), RatingReviewsState

    data class Loading(
        override val titleText: TextSource
    ) : BaseLoadingState(), RatingReviewsState

    data class Content(
        override val titleText: TextSource,
        val imageUrl: String,
        val sortTitleText: TextSource,
        val selectedSortItem: RatingReviewSortItem,
        val availableSorts: ImmutableList<RatingReviewSortItem>,
        val timeFrameTitle: TextSource,
        val selectedTimeFrameItem: RatingReviewTimeframeItem,
        val availableTimeFrames: ImmutableList<RatingReviewTimeframeItem>,
        val reviewItems: ImmutableList<RatingReviewListItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        private val onLoadMore: () -> Unit,
        private val onSelectedSort: (RatingReviewSortItem) -> Unit,
        private val onSelectedTimeframe: (RatingReviewTimeframeItem) -> Unit,
    ) : RatingReviewsState {
        fun selectedSort(sort: RatingReviewSortItem) = onSelectedSort(sort)
        fun selectedTimeFrame(timeframeItem: RatingReviewTimeframeItem) = onSelectedTimeframe(timeframeItem)
        fun loadMore() = onLoadMore()
    }
}

@Suppress("FunctionName")
fun RatingReviewsStateContent_PreviewData(): RatingReviewsState.Content =
    RatingReviewsState.Content(
        titleText = "Game Player Reviews".asTextSource(),
        imageUrl = "https://img.opencritic.com/game/17126/z2psJiOW.jpg",
        sortTitleText = "Sort".asTextSource(),
        selectedSortItem = RatingReviewSortItem(
            key = RatingReviewSorting.Newest,
            text = RatingReviewSorting.Newest.asTextSource()
        ),
        availableSorts = RatingReviewSorting.entries
            .map { RatingReviewSortItem(it, it.asTextSource()) }
            .toImmutableList(),
        timeFrameTitle = "Timeframe".asTextSource(),
        selectedTimeFrameItem = RatingReviewTimeframeItem(
            key = RatingTimeframe.AllTime,
            text = RatingTimeframe.AllTime.asTextSource(),
        ),
        availableTimeFrames = RatingTimeframe.entries
            .map { RatingReviewTimeframeItem(it, it.asTextSource()) }
            .toImmutableList(),
        reviewItems = List(10) {
            RatingReviewListItem_PreviewData(it.toString())
        }.toImmutableList(),
        isLoadingItemVisible = false,
        loadingItem = LoadingItem,
        onLoadMore = {},
        onSelectedSort = {},
        onSelectedTimeframe = {},
    )