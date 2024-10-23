package com.opencritic.games.details.ui.reviews.rating

import com.opencritic.games.Game
import com.opencritic.games.details.api.ui.RatingReviewsRoute
import com.opencritic.games.details.domain.RatingReviewSorting
import com.opencritic.games.details.domain.RatingTimeframe
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.games.details.domain.interactor.GetGameInteractor
import com.opencritic.games.details.domain.interactor.GetGameRatingReviewsInteractor
import com.opencritic.games.details.ui.LoadingItem
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class RatingReviewsViewModel(
    private val args: RatingReviewsRoute.InitArgs,
    private val getGameInteractor: GetGameInteractor,
    private val getGameReviewsInteractor: GetGameRatingReviewsInteractor,
    private val logger: Logger,
) : BaseViewModel<RatingReviewsState>() {
    override fun initialState(): RatingReviewsState =
        RatingReviewsState.Loading(titleOf(args.gameName))

    private var game: Game? = null
    private var canLoadMore: Boolean = true
    private var page: Int = 1
    private val take: Int = 20
    private var sorting: RatingReviewSorting = RatingReviewSorting.Newest
    private var timeframe: RatingTimeframe = RatingTimeframe.AllTime

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getGameInteractor(args.gameId)
                .onFailure {
                    mutableState.tryEmit(
                        RatingReviewsState.Error(
                            titleText = titleOf(args.gameName),
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
                getGameReviewsInteractor(
                    gameId = args.gameId,
                    sorting = sorting,
                    page = page,
                    limit = take,
                    timeframe = timeframe,
                )
                    .onFailure {
                        logger.log(it.toString())
                    }
                    .onSuccess { reviews ->
                        requireNotNull(state.value as? RatingReviewsState.Content)
                            .let { content ->
                                content.copy(
                                    reviewItems = content.reviewItems.mapAndAdd(reviews),
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
    ): RatingReviewsState.Content =
        RatingReviewsState.Content(
            titleText = titleOf(game.name),
            imageUrl = game.bannerImageUrl,
            sortTitleText = StringRes.str_rating_reviews_sort_title.asTextSource(),
            selectedSortItem = RatingReviewSortItem(
                key = RatingReviewSorting.Newest,
                text = RatingReviewSorting.Newest.asTextSource()
            ),
            availableSorts = RatingReviewSorting.entries
                .map { RatingReviewSortItem(it, it.asTextSource()) }
                .toImmutableList(),
            timeFrameTitle = StringRes.str_rating_reviews_timeframe_title.asTextSource(),
            selectedTimeFrameItem = RatingReviewTimeframeItem(
                key = RatingTimeframe.AllTime,
                text = RatingTimeframe.AllTime.asTextSource(),
            ),
            availableTimeFrames = RatingTimeframe.entries
                .map { RatingReviewTimeframeItem(it, it.asTextSource()) }
                .toImmutableList(),
            reviewItems = persistentListOf(),
            isLoadingItemVisible = true,
            loadingItem = LoadingItem,
            onLoadMore = ::loadMore,
            onSelectedSort = ::onSortSelected,
            onSelectedTimeframe = ::onTimeframeSelected,
        )

    private fun loadMore() {
        if (!canLoadMore)
            return

        val state = requireNotNull(state.value as? RatingReviewsState.Content)

        scope.launch {
            getGameReviewsInteractor(
                gameId = args.gameId,
                sorting = sorting,
                page = page,
                limit = take,
                timeframe = timeframe,
            )
                .onSuccess { reviews ->
                    page++
                    state.copy(
                        reviewItems = state.reviewItems.mapAndAdd(reviews),
                        isLoadingItemVisible = (state.reviewItems.size + reviews.size < (game?.reviewsCount ?: 0)) && reviews.isNotEmpty()
                    ).let {
                        mutableState.tryEmit(it)
                    }
                }
        }
    }

    private fun onSortSelected(item: RatingReviewSortItem) {
        if (item.key == sorting)
            return

        sorting = item.key
        canLoadMore = true
        page = 1

        val state = requireNotNull(state.value as? RatingReviewsState.Content)

        mutableState.tryEmit(
            state.copy(
                selectedSortItem = item,
                reviewItems = persistentListOf(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun onTimeframeSelected(item: RatingReviewTimeframeItem) {
        if (item.key == timeframe)
            return

        timeframe = item.key
        canLoadMore = true
        page = 1

        val state = requireNotNull(state.value as? RatingReviewsState.Content)

        mutableState.tryEmit(
            state.copy(
                selectedTimeFrameItem = item,
                reviewItems = persistentListOf(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun titleOf(gameName: String): TextSource =
        StringRes.str_rating_reviews_screen_title.asTextSource(gameName)
}