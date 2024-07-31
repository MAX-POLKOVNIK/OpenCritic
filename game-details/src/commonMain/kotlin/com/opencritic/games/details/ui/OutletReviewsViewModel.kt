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
import com.opencritic.resources.Icons
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getFormattedString
import com.opencritic.resources.getString
import kotlinx.coroutines.launch

class OutletReviewsViewModel(
    private val outletId: Int,
    private val outletName: String,
    private val getOutletInteractor: GetOutletInteractor,
    private val getOutletReviewsInteractor: GetOutletReviewsInteractor,
    private val stringProvider: StringProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<OutletReviewsState>() {
    override fun initialState(): OutletReviewsState =
        OutletReviewsState.Loading(stringProvider.getFormattedString(StringRes.str_reviews_of, outletName))

    private var outlet: Outlet? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getOutletInteractor(outletId)
                .onFailure {
                    mutableState.tryEmit(
                        OutletReviewsState.Error(stringProvider.getFormattedString(StringRes.str_reviews_of, outletName), it.toString())
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
            titleText = stringProvider.getFormattedString(StringRes.str_reviews_of, outlet.name),
            outletNameText = outlet.name,
            outletImageUrl = outlet.imageUrl,
            isHomepageVisible = outlet.externalUrl.isNotBlank(),
            homepageText = stringProvider.getString(StringRes.str_home_page),
            sortTitleText = stringProvider.getString(StringRes.str_sort),
            infoItems = listOf(
                IconTextItem(
                    icon = Icons.hashTag,
                    text = stringProvider.getFormattedString(StringRes.str_games_reviewed_formatted, outlet.reviewsCount.toString()),
                ),
                IconTextItem(
                    icon = Icons.chartPie,
                    text = stringProvider.getFormattedString(StringRes.str_average_score_formatted, outlet.averageScore.toInt().toString()),
                ),
                IconTextItem(
                    icon = Icons.bullseye,
                    text = stringProvider.getFormattedString(StringRes.str_median_score_formatted, outlet.medianScore.toString())
                ),
                IconTextItem(
                    icon = Icons.thumbUp,
                    text = stringProvider.getFormattedString(StringRes.str_games_recommended_formatted, outlet.percentRecommended.toInt().toString())
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