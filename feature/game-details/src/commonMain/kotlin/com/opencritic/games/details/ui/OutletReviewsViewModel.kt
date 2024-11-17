package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.games.details.api.ui.AuthorReviewsRoute
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.details.api.ui.OutletReviewsRoute
import com.opencritic.games.details.domain.Outlet
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.games.details.domain.interactor.GetOutletInteractor
import com.opencritic.games.details.domain.interactor.GetOutletReviewsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.navigation.asUrlRouteArgs
import com.opencritic.remote.images.ImagePreloader
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class OutletReviewsViewModel(
    private val args: OutletReviewsRoute.InitArgs,
    private val getOutletInteractor: GetOutletInteractor,
    private val getOutletReviewsInteractor: GetOutletReviewsInteractor,
    private val logger: Logger,
    private val imagePreloader: ImagePreloader,
) : BaseViewModel<OutletReviewsState>() {
    override fun initialState(): OutletReviewsState =
        OutletReviewsState.Loading(StringRes.str_reviews_of.asTextSource(args.outletName))

    private var outlet: Outlet? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    override fun onCleared() {
        super.onCleared()

        imagePreloader.cancel()
    }

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getOutletInteractor(args.outletId)
                .onFailure {
                    mutableState.tryEmit(
                        OutletReviewsState.Error(
                            titleText = StringRes.str_reviews_of.asTextSource(args.outletName),
                            message = it.toString().asTextSource()
                        )
                    )
                }
                .onSuccess {
                    mutableState.tryEmit(
                        createContentState(it)
                    )

                    outlet = it
                }

            if (outlet != null) {
                getOutletReviewsInteractor(args.outletId, skip = 0, sort = sorting)
                    .onFailure {
                        logger.log(it.toString())
                    }
                    .onSuccess { reviews ->
                        requireNotNull(state.value as? OutletReviewsState.Content)
                            .let { content ->
                                content.copy(
                                    reviewItems = content.reviewItems.mapAndAdd(reviews, ::ReviewListItem),
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
            titleText = StringRes.str_reviews_of.asTextSource(outlet.name),
            outletNameText = outlet.name,
            outletImageUrl = outlet.imageUrl,
            isHomepageVisible = outlet.externalUrl.isNotBlank(),
            homepageText = StringRes.str_home_page.asTextSource(),
            sortTitleText = StringRes.str_sort.asTextSource(),
            infoItems = persistentListOf(
                IconTextItem(
                    icon = Icons.hashTag,
                    text = StringRes.str_games_reviewed_formatted.asTextSource(outlet.reviewsCount.toString()),
                ),
                IconTextItem(
                    icon = Icons.chartPie,
                    text = StringRes.str_average_score_formatted.asTextSource(outlet.averageScore.toInt().toString()),
                ),
                IconTextItem(
                    icon = Icons.bullseye,
                    text = StringRes.str_median_score_formatted.asTextSource(outlet.medianScore.toString())
                ),
                IconTextItem(
                    icon = Icons.thumbUp,
                    text = StringRes.str_games_recommended_formatted.asTextSource(outlet.percentRecommended.toInt().toString())
                )
            ),
            sortText = ReviewSortItem(
                key = ReviewSorting.Default,
                name = ReviewSorting.Default.asTextSource()
            ),
            availableSorts = ReviewSorting.entries
                .filter { it != ReviewSorting.MostPopular }
                .map { ReviewSortItem(it, it.asTextSource()) }
                .toImmutableList(),
            reviewItems = persistentListOf(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = ::loadMore,
            onSelectedSort = ::onSortSelected,
            onHomepageClick = ::onHomePageClick,
        )

    private fun loadMore() {
        if (!canLoadMore)
            return

        val state = requireNotNull(state.value as? OutletReviewsState.Content)

        scope.launch {
            getOutletReviewsInteractor(args.outletId, state.reviewItems.size, sorting)
                .onSuccess { reviews ->
                    state.copy(
                        reviewItems = state.reviewItems.mapAndAdd(reviews, ::ReviewListItem),
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
            onClick = ::openUrl,
            onAuthorClick = ::openAuthor,
            onGameClick = :: openGame,
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
                reviewItems = persistentListOf(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun onHomePageClick() {
        val url = outlet?.externalUrl ?: return

        UrlRoute.navigate(url.asUrlRouteArgs())
    }

    private fun openUrl(item: ReviewListItem) {
        UrlRoute.navigate(item.externalUrl.asUrlRouteArgs())
    }

    private fun openGame(item: ReviewListItem) {
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(item.gameId, item.gameText)
        )
    }

    private fun openAuthor(item: ReviewListItem) {
        val authorId = item.authorId ?: return

        AuthorReviewsRoute.navigate(
            AuthorReviewsRoute.InitArgs(authorId, item.authorText)
        )
    }
}