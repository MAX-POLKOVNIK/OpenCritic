package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.games.details.domain.Outlet
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.games.details.domain.interactor.GetOutletInteractor
import com.opencritic.games.details.domain.interactor.GetOutletReviewsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.AuthorReviewsRoute
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class OutletReviewsViewModel(
    private val outletId: Int,
    private val outletName: String,
    private val getOutletInteractor: GetOutletInteractor,
    private val getOutletReviewsInteractor: GetOutletReviewsInteractor,
    private val logger: Logger,
) : BaseViewModel<OutletReviewsState>() {
    override fun initialState(): OutletReviewsState =
        OutletReviewsState.Loading(StringRes.str_reviews_of.asTextSource(outletName))

    private var outlet: Outlet? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getOutletInteractor(outletId)
                .onFailure {
                    mutableState.tryEmit(
                        OutletReviewsState.Error(
                            titleText = StringRes.str_reviews_of.asTextSource(outletName),
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
            titleText = StringRes.str_reviews_of.asTextSource(outlet.name),
            outletNameText = outlet.name,
            outletImageUrl = outlet.imageUrl,
            isHomepageVisible = outlet.externalUrl.isNotBlank(),
            homepageText = StringRes.str_home_page.asTextSource(),
            sortTitleText = StringRes.str_sort.asTextSource(),
            infoItems = listOf(
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
                key = ReviewSorting.Default, name = ReviewSorting.Default.asTextSource()
            ),
            availableSorts = ReviewSorting.entries
                .filter { it != ReviewSorting.MostPopular }
                .map { ReviewSortItem(it, it.asTextSource()) },
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
            onClick = { openUrl(review.externalUrl) },
            onAuthorClick = {
                review.authors.firstOrNull()
                    ?.let { openAuthor(it.id, it.name) }
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

    private fun openAuthor(authorId: Int, authorName: String) {
        requireRouter()
            .navigateTo(
                AuthorReviewsRoute(authorId, authorName)
            )
    }
}