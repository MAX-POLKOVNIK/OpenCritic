package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.Icons

interface AuthorReviewsState : ViewModelState {
    val titleText: String

    data class Error(
        override val titleText: String,
        override val message: String
    ) : BaseErrorState(message), AuthorReviewsState

    data class Loading(override val titleText: String) : BaseLoadingState(), AuthorReviewsState

    data class Content(
        override val titleText: String,
        val nameText: String,
        val imageUrl: String,
        val isBioVisible: Boolean,
        val bioText: String,
        val isFavoritesGamesVisible: Boolean,
        val favoritesGamesTitleText: String,
        val favoritesGames: List<String>,
        val personalInfoItems: List<IconTextItem>,
        val countersInfoItems: List<IconTextItem>,
        val sortTitleText: String,
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
        titleText = "Travis Northup's Reviews",
        nameText = "Travis Northup",
        bioText = "I wear a suit every day and play too many video games.",
        isBioVisible = true,
        imageUrl = "https://img.opencritic.com/critic/481/CayQVcgn.jpg",
        sortTitleText = "Sort",
        countersInfoItems = listOf(
            IconTextItem(
                icon = Icons.hashTag,
                text = "178 games reviewed",
            ),
            IconTextItem(
                icon = Icons.chartPie,
                text = "74.4 average score",
            ),
            IconTextItem(
                icon = Icons.bullseye,
                text = "80 median score",
            ),
            IconTextItem(
                icon = Icons.thumbUp,
                text = "65.2% of games recommended"
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
        onLoadMore = {  },
        onSelectedSort = {  },
        isFavoritesGamesVisible = true,
        favoritesGamesTitleText = "Favorite Games",
        favoritesGames = listOf(
            "Halo 2",
            "Minecraft",
            "The Elder Scrolls III: Morrowind",
        ),
        personalInfoItems = listOfNotNull(
            IconTextItem(
                icon = Icons.home,
                text = "San Francisco"
            ),
            IconTextItem(
                icon = Icons.xbox,
                text = "TieGuyTravis"
            ),
            IconTextItem(
                icon = Icons.playstation,
                text = "TieGuyTravis"
            ),
        )
    )