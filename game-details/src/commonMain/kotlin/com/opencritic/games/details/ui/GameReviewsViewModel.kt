package com.opencritic.games.details.ui

import com.opencritic.games.Game
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.domain.GetGameInteractor
import com.opencritic.games.details.domain.GetGameReviewsInteractor
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch

class GameReviewsViewModel(
    private val gameId: Long,
    private val getGameInteractor: GetGameInteractor,
    private val getGameReviewsInteractor: GetGameReviewsInteractor,
    private val imageResourceProvider: ImageResourceProvider,
    private val stringProvider: StringProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<GameReviewsState>() {
    override val initialState: GameReviewsState
        get() = GameReviewsState.Loading

    private var game: Game? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    init {
        scope.launch {
            getGameInteractor(gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameReviewsState.Error(it.toString())
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
                                            stringProvider = stringProvider,
                                            dateFormatter = dateFormatter,
                                            onClick = {},
                                            onAuthorClick = {},
                                            onImageClick = {},
                                            onOutletClick = {},
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
            titleText = game.name,
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
            rankedDescription = "${game.name} is ranked in the ${game.recommendPercent ?: 0}th percentile of games scored on OpenCritic.",
            sortTitleText = "Sort",
            sortText = ReviewSortItem(ReviewSorting.Default, ReviewSorting.Default.name),
            availableSorts = ReviewSorting.entries.map { ReviewSortItem(it, it.name) },
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
                                stringProvider = stringProvider,
                                dateFormatter = dateFormatter,
                                onClick = {},
                                onAuthorClick = {},
                                onImageClick = {},
                                onOutletClick = {},
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
                reviewItems = emptyList(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }
}