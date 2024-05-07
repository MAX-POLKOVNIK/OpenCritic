package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.games.details.domain.Outlet
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.interactor.GetOutletInteractor
import com.opencritic.games.details.domain.interactor.GetOutletReviewsInteractor
import com.opencritic.games.details.domain.sortNameOf
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.AuthorReviewsRoute
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch

class OutletReviewsViewModel(
    private val outletId: Int,
    private val outletName: String,
    private val getOutletInteractor: GetOutletInteractor,
    private val getOutletReviewsInteractor: GetOutletReviewsInteractor,
    private val imageResourceProvider: ImageResourceProvider,
    private val stringProvider: StringProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<OutletReviewsState>() {
    override val initialState: OutletReviewsState =
        OutletReviewsState.Loading(stringProvider.reviewsOf(outletName))

    private var outlet: Outlet? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    init {
        scope.launch {
            getOutletInteractor(outletId)
                .onFailure {
                    mutableState.tryEmit(
                        OutletReviewsState.Error(stringProvider.reviewsOf(outletName), it.toString())
                    )
                }
                .onSuccess {
                    mutableState.tryEmit(
                        createContentState(it)
                    )

                    outlet = it
                }

            if (outlet != null) {
                getOutletReviewsInteractor(outletId, skip = 0, sort = sorting)
                    .onFailure {
                        logger.log(it.toString())
                    }
                    .onSuccess { reviews ->
                        requireNotNull(state.value as? OutletReviewsState.Content)
                            .let { content ->
                                content.copy(
                                    reviewItems = content.reviewItems + reviews.map { review ->
                                        ReviewListItem(review)
                                    },
                                    isLoadingItemVisible = content.reviewItems.size + reviews.size < (outlet?.reviewsCount ?: 0)
                                )
                            }
                            .let {
                                mutableState.tryEmit(it)
                            }

                        canLoadMore = reviews.isNotEmpty()
                    }
            }
        }
    }

    private fun createContentState(
        outlet: Outlet,
    ): OutletReviewsState.Content =
        OutletReviewsState.Content(
            titleText = stringProvider.reviewsOf(outlet.name),
            outletNameText = outlet.name,
            outletImageUrl = outlet.imageUrl,
            isHomepageVisible = outlet.externalUrl.isNotBlank(),
            homepageText = stringProvider.homepage,
            sortTitleText = stringProvider.sort,
            infoItems = listOf(
                IconTextItem(
                    icon = imageResourceProvider.hashTag,
                    text = stringProvider.gamesReviewedFormatted(outlet.reviewsCount.toString()),
                ),
                IconTextItem(
                    icon = imageResourceProvider.chartPie,
                    text = stringProvider.averageScoreFormatted(outlet.averageScore.toInt().toString()),
                ),
                IconTextItem(
                    icon = imageResourceProvider.bullseye,
                    text = stringProvider.medianScoreFormatted(outlet.medianScore.toString())
                ),
                IconTextItem(
                    icon = imageResourceProvider.thumbUp,
                    text = stringProvider.gamesRecommendedFormatted(outlet.percentRecommended.toInt().toString())
                )
            ),
            sortText = ReviewSortItem(
                key = ReviewSorting.Default, name = stringProvider.sortNameOf(ReviewSorting.Default)
            ),
            availableSorts = ReviewSorting.entries
                .filter { it != ReviewSorting.MostPopular }
                .map { ReviewSortItem(it, stringProvider.sortNameOf(it)) },
            reviewItems = emptyList(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = { loadMore() },
            onSelectedSort = { onSortSelected(it) },
            onHomepageClick = { openUrl(outlet.externalUrl) }
        )

    private fun loadMore() {
        if (!canLoadMore)
            return

        val state = requireNotNull(state.value as? OutletReviewsState.Content)

        scope.launch {
            getOutletReviewsInteractor(outletId, state.reviewItems.size, sorting)
                .onSuccess { reviews ->
                    state.copy(
                        reviewItems = state.reviewItems + reviews.map { review ->
                            ReviewListItem(review)
                        },
                        isLoadingItemVisible = state.reviewItems.size + reviews.size < (outlet?.reviewsCount ?: 0)
                    ).let {
                        mutableState.tryEmit(it)
                    }
                }
        }
    }

    private fun ReviewListItem(review: Review): ReviewListItem =
        ReviewListItem(
            review = review,
            isGameVisible = true,
            stringProvider = stringProvider,
            dateFormatter = dateFormatter,
            onClick = { openUrl(review.externalUrl) },
            onAuthorClick = {
                review.authors.firstOrNull()
                    ?.let { openAuthor(it.id) }
            },
            onImageClick = {},
            onOutletClick = {},
            onGameClick = { openGame(review.gameId, review.gameName) },
        )

    private fun onSortSelected(item: ReviewSortItem) {
        if (item.key == sorting)
            return

        sorting = item.key
        canLoadMore = true

        val state = requireNotNull(state.value as? OutletReviewsState.Content)

        mutableState.tryEmit(
            state.copy(
                sortText = item,
                reviewItems = emptyList(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun openUrl(url: String) {
        requireRouter()
            .navigateTo(UrlRoute(url))
    }

    private fun openGame(gameId: Long, gameName: String) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId, gameName)
            )
    }

    private fun openAuthor(authorId: Int) {
        requireRouter()
            .navigateTo(
                AuthorReviewsRoute(authorId)
            )
    }
}