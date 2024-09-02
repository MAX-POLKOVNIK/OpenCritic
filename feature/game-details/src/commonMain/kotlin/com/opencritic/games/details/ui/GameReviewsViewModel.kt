package com.opencritic.games.details.ui

import com.opencritic.games.Game
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.api.ui.AuthorReviewsRoute
import com.opencritic.games.details.api.ui.GameReviewsRoute
import com.opencritic.games.details.api.ui.OutletReviewsRoute
import com.opencritic.games.details.domain.interactor.GetGameInteractor
import com.opencritic.games.details.domain.interactor.GetGameReviewsInteractor
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.navigation.asUrlRouteArgs
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class GameReviewsViewModel(
    private val args: GameReviewsRoute.InitArgs,
    private val getGameInteractor: GetGameInteractor,
    private val getGameReviewsInteractor: GetGameReviewsInteractor,
    private val logger: Logger,
) : BaseViewModel<GameReviewsState>() {
    override fun initialState(): GameReviewsState =
        GameReviewsState.Loading(StringRes.str_game_reviews.asTextSource(args.gameName))

    private var game: Game? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getGameInteractor(args.gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameReviewsState.Error(
                            titleText = StringRes.str_game_reviews.asTextSource(args.gameName),
                            message = it.toString().asTextSource()
                        )
                    )
                }
                .onSuccess {
                    mutableState.tryEmit(
                        createContentState(it)
                    )

                    game = it
                }

            if (game != null) {
                getGameReviewsInteractor(args.gameId, sorting = sorting)
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
                                            onClick = { openUrl(review.externalUrl) },
                                            onAuthorClick = {
                                                review.authors.firstOrNull()
                                                    ?.let { openAuthor(it.id, it.name) }
                                            },
                                            onImageClick = {},
                                            onOutletClick = { openOutlet(review.outlet.id, review.outlet.name) },
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
    ): GameReviewsState.Content =
        GameReviewsState.Content(
            titleText = StringRes.str_game_reviews.asTextSource(game.name),
            imageUrl = game.bannerImageUrl,
            isTierVisible = game.rank != null,
            tierImageResource = when (game.rank?.tier) {
                Tier.Mighty -> SharedImages.mightyMan
                Tier.Strong -> SharedImages.strongMan
                Tier.Fair -> SharedImages.fairMan
                Tier.Weak -> SharedImages.weakMan
                null -> SharedImages.weakMan
            },
            isTopScoreIndicatorVisible = game.rank != null,
            topScoreIndicator = createTopCriticAverageIndicator(
                gameRank = game.rank ?: GameRank(Tier.Weak, 0)
            ),
            isRecommendIndicatorVisible = game.rank != null,
            recommendScoreIndicator = createCriticsRecommendIndicator(
                tier = game.rank?.tier ?: Tier.Weak, game.recommendPercent ?: 0f
            ),
            isRankedDescriptionVisible = game.recommendPercent != null,
            rankedDescription = StringRes.str_game_review_ranked_description.asTextSource(game.name, (game.recommendPercent ?: 0).toString()),
            sortTitleText = StringRes.str_sort.asTextSource(),
            sortText = ReviewSortItem(
                key = ReviewSorting.Default, name = ReviewSorting.Default.asTextSource()
            ),
            availableSorts = ReviewSorting.entries.map { ReviewSortItem(it, it.asTextSource()) },
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
            getGameReviewsInteractor(args.gameId, state.reviewItems.size, sorting)
                .onSuccess { reviews ->
                    state.copy(
                        reviewItems = state.reviewItems + reviews.map { review ->
                            ReviewListItem(
                                review = review,
                                isGameVisible = false,
                                onClick = { openUrl(review.externalUrl) },
                                onAuthorClick = {
                                    review.authors.firstOrNull()
                                        ?.let { openAuthor(it.id, it.name) }
                                },
                                onImageClick = {},
                                onOutletClick = { openOutlet(review.outlet.id, review.outlet.name) },
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
        UrlRoute.navigate(url.asUrlRouteArgs())
    }

    private fun openOutlet(outletId: Int, outletName: String) {
        OutletReviewsRoute.navigate(
            OutletReviewsRoute.InitArgs(outletId, outletName)
        )
    }

    private fun openAuthor(authorId: Int, authorName: String) {
        AuthorReviewsRoute.navigate(
            AuthorReviewsRoute.InitArgs(authorId, authorName)
        )
    }
}