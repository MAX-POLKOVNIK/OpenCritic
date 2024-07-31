package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

interface AuthorReviewsState : ViewModelState {
    val titleText: TextSource

    data class Error(
        override val titleText: TextSource,
        override val message: String
    ) : BaseErrorState(message), AuthorReviewsState

    data class Loading(override val titleText: TextSource) : BaseLoadingState(), AuthorReviewsState

    data class Content(
        override val titleText: TextSource,
        val nameText: String,
        val imageUrl: String,
        val isBioVisible: Boolean,
        val bioText: TextSource,
        val isFavoritesGamesVisible: Boolean,
        val favoritesGamesTitleText: TextSource,
        val favoritesGames: List<String>,
        val personalInfoItems: List<IconTextItem>,
        val countersInfoItems: List<IconTextItem>,
        val sortTitleText: TextSource,
        val sortText: ReviewSortItem,
        val availableSorts: List<ReviewSortItem>,
        val reviewItems: List<ReviewListItem>,
        val isLoadingItemVisible: Boolean,
        val loadingItem: LoadingItem,
        val onLoadMore: () -> Unit,
        val onSelectedSort: (ReviewSortItem) -> Unit,
    ) : AuthorReviewsState
}

@Suppress("FunctionName")
fun AuthorReviewsStateContent_PreviewData(): AuthorReviewsState.Content =
    AuthorReviewsState.Content(
        titleText = "Travis Northup's Reviews".asTextSource(),
        nameText = "Travis Northup",
        bioText = "I wear a suit every day and play too many video games.".asTextSource(),
        isBioVisible = true,
        imageUrl = "https://img.opencritic.com/critic/481/CayQVcgn.jpg",
        sortTitleText = "Sort".asTextSource(),
        countersInfoItems = listOf(
            IconTextItem(
                icon = Icons.hashTag,
                text = "178 games reviewed".asTextSource(),
            ),
            IconTextItem(
                icon = Icons.chartPie,
                text = "74.4 average score".asTextSource(),
            ),
            IconTextItem(
                icon = Icons.bullseye,
                text = "80 median score".asTextSource(),
            ),
            IconTextItem(
                icon = Icons.thumbUp,
                text = "65.2% of games recommended".asTextSource()
            )
        ),
        sortText = ReviewSortItem(
            key = ReviewSorting.Default, name = ReviewSorting.Default.asTextSource()
        ),
        availableSorts = ReviewSorting.entries
            .filter { it != ReviewSorting.MostPopular }
            .map { ReviewSortItem(it, it.asTextSource()) },
        reviewItems = emptyList(),
        isLoadingItemVisible = true,
        loadingItem = LoadingItem,
        onLoadMore = {  },
        onSelectedSort = {  },
        isFavoritesGamesVisible = true,
        favoritesGamesTitleText = "Favorite Games".asTextSource(),
        favoritesGames = listOf(
            "Halo 2",
            "Minecraft",
            "The Elder Scrolls III: Morrowind",
        ),
        personalInfoItems = listOfNotNull(
            IconTextItem(
                icon = Icons.home,
                text = "San Francisco".asTextSource()
            ),
            IconTextItem(
                icon = Icons.xbox,
                text = "TieGuyTravis".asTextSource()
            ),
            IconTextItem(
                icon = Icons.playstation,
                text = "TieGuyTravis".asTextSource()
            ),
        )
    )