package com.opencritic.games.details.ui

import com.opencritic.games.Game
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.domain.interactor.GetGameInteractor
import com.opencritic.games.details.domain.interactor.GetGameReviewsInteractor
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.sortNameOf
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.AuthorReviewsRoute
import com.opencritic.navigation.OutletReviewsRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch

class GameReviewsViewModel(
    private val gameId: Long,
    private val gameName: String,
    private val getGameInteractor: GetGameInteractor,
    private val getGameReviewsInteractor: GetGameReviewsInteractor,
    private val imageResourceProvider: ImageResourceProvider,
    private val stringProvider: StringProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<GameReviewsState>() {
    override val initialState: GameReviewsState
        get() = GameReviewsState.Loading("$gameName ${stringProvider.reviews}")

    private var game: Game? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    init {
        scope.launch {
            getGameInteractor(gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameReviewsState.Error(
                            titleText = "$gameName ${stringProvider.reviews}",
                            message = it.toString()
                        )
                    )
                }
                .onSuccess {
                    mutableState.tryEmit(
                        createContentState(it, imageResourceProvider)
                    )

                    game = it
                }

            if (game != null) {
                getGameReviewsInteractor(gameId, sorting = sorting)
                    .onFailure {
                        logger.log(it.toString())
                    }
                    .onSuccess { reviews ->
                        requireNotNull(state.value as? GameReviewsState.Content)
                            .let { content ->
                                content.copy(
                                    reviewItems = content.reviewItems + reviews.map { review ->
                                        ReviewListItem(
                                            review = review,
                                            isGameVisible = false,
                                            stringProvider = stringProvider,
                                            dateFormatter = dateFormatter,
                                            onClick = { openUrl(review.externalUrl) },
                                            onAuthorClick = {
                                                review.authors.firstOrNull()
                                                    ?.let { openAuthor(it.id) }
                                            },
                                            onImageClick = {},
                                            onOutletClick = { openOutlet(review.outlet.id) },
                                            onGameClick = {},
                                        )
                                    },
                                    isLoadingItemVisible = content.reviewItems.size + reviews.size < (game?.reviewsCount ?: 0)
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
        game: Game,
        imageResourceProvider: ImageResourceProvider,
    ): GameReviewsState.Content =
        GameReviewsState.Content(
            titleText = "${game.name} ${stringProvider.reviews}",
            imageUrl = game.bannerImageUrl,
            isTierVisible = game.rank != null,
            tierImageResource = when (game.rank?.tier) {
                Tier.Mighty -> imageResourceProvider.mightyMan
                Tier.Strong -> imageResourceProvider.strongMan
                Tier.Fair -> imageResourceProvider.fairMan
                Tier.Weak -> imageResourceProvider.weakMan
                null -> imageResourceProvider.weakMan
            },
            isTopScoreIndicatorVisible = game.rank != null,
            topScoreIndicator = createTopCriticAverageIndicator(
                game.rank ?: GameRank(Tier.Weak, 0)
            ),
            isRecommendIndicatorVisible = game.rank != null,
            recommendScoreIndicator = createCriticsRecommendIndicator(
                game.rank?.tier ?: Tier.Weak, game.recommendPercent ?: 0
            ),
            isRankedDescriptionVisible = game.recommendPercent != null,
            rankedDescription = stringProvider.gameReviewRankedDescription(game.name, (game.recommendPercent ?: 0).toString()),
            sortTitleText = stringProvider.sort,
            sortText = ReviewSortItem(
                key = ReviewSorting.Default, name = stringProvider.sortNameOf(ReviewSorting.Default)
            ),
            availableSorts = ReviewSorting.entries.map { ReviewSortItem(it, stringProvider.sortNameOf(it)) },
            reviewItems = emptyList(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = { loadMore() },
            onSelectedSort = { onSortSelected(it) },
        )

    private fun loadMore() {
        if (!canLoadMore)
            return

        val state = requireNotNull(state.value as? GameReviewsState.Content)

        scope.launch {
            getGameReviewsInteractor(gameId, state.reviewItems.size, sorting)
                .onSuccess { reviews ->
                    state.copy(
                        reviewItems = state.reviewItems + reviews.map { review ->
                            ReviewListItem(
                                review = review,
                                isGameVisible = false,
                                stringProvider = stringProvider,
                                dateFormatter = dateFormatter,
                                onClick = { openUrl(review.externalUrl) },
                                onAuthorClick = {
                                    review.authors.firstOrNull()
                                        ?.let { openAuthor(it.id) }
                                },
                                onImageClick = {},
                                onOutletClick = { openOutlet(review.outlet.id) },
                                onGameClick = {},
                            )
                        },
                        isLoadingItemVisible = state.reviewItems.size + reviews.size < (game?.reviewsCount ?: 0)
                    ).let {
                        mutableState.tryEmit(it)
                    }
                }
        }
    }

    private fun onSortSelected(item: ReviewSortItem) {
        if (item.key == sorting)
            return

        sorting = item.key
        canLoadMore = true

        val state = requireNotNull(state.value as? GameReviewsState.Content)

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

    private fun openOutlet(outletId: Int) {
        requireRouter()
            .navigateTo(
                OutletReviewsRoute(outletId)
            )
    }

    private fun openAuthor(authorId: Int) {
        requireRouter()
            .navigateTo(
                AuthorReviewsRoute(authorId)
            )
    }
}