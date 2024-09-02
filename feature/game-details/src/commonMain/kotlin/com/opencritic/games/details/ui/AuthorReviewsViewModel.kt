package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.games.details.api.ui.AuthorReviewsRoute
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.details.domain.Author
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.asTextSource
import com.opencritic.games.details.domain.interactor.GetAuthorInteractor
import com.opencritic.games.details.domain.interactor.GetAuthorReviewsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.navigation.asUrlRouteArgs
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorReviewsViewModel(
    private val args: AuthorReviewsRoute.InitArgs,
    private val getAuthorInteractor: GetAuthorInteractor,
    private val getAuthorReviewsInteractor: GetAuthorReviewsInteractor,
    private val logger: Logger,
) : BaseViewModel<AuthorReviewsState>() {
    override fun initialState(): AuthorReviewsState =
        AuthorReviewsState.Loading(StringRes.str_reviews_of.asTextSource(args.authorName))

    private var author: Author? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getAuthorInteractor(args.authorId)
                .onFailure {
                    mutableState.update {
                        AuthorReviewsState.Error(
                            StringRes.str_reviews_of.asTextSource(args.authorName),
                            it.toString().asTextSource()
                        )
                    }
                }
                .onSuccess { author ->
                    mutableState.update {
                        createContentState(author)
                    }

                    this@AuthorReviewsViewModel.author = author
                }

            if (author != null) {
                getAuthorReviewsInteractor(args.authorId, skip = 0, sort = sorting)
                    .onFailure {
                        logger.log(it.toString())
                    }
                    .onSuccess { reviews ->
                        requireNotNull(state.value as? AuthorReviewsState.Content)
                            .let { content ->
                                content.copy(
                                    reviewItems = content.reviewItems + reviews.map { review ->
                                        ReviewListItem(review)
                                    },
                                    isLoadingItemVisible = content.reviewItems.size + reviews.size < (author?.reviewCount ?: 0)
                                )
                            }
                            .let { content ->
                                mutableState.update { content }
                            }

                        canLoadMore = reviews.isNotEmpty()
                    }
            }
        }
    }

    private fun createContentState(
        author: Author,
    ): AuthorReviewsState.Content =
        AuthorReviewsState.Content(
            titleText = StringRes.str_reviews_of.asTextSource(author.name),
            nameText = author.name,
            bioText =
                if (author.isClaimed) author.bio.asTextSource()
                else StringRes.str_author_is_not_claimed.asTextSource(author.name),
            isBioVisible = !author.isClaimed || author.bio.isNotBlank(),
            imageUrl = author.imageUrl,
            sortTitleText = StringRes.str_sort.asTextSource(),
            countersInfoItems = listOfNotNull(
                IconTextItem(
                    icon = Icons.hashTag,
                    text = StringRes.str_games_reviewed_formatted.asTextSource(author.reviewCount.toString()),
                ).takeIf { author.reviewCount > 0 },
                IconTextItem(
                    icon = Icons.chartPie,
                    text = StringRes.str_average_score_formatted.asTextSource(author.averageScore.toInt().toString()),
                ).takeIf { author.averageScore > 0 },
                IconTextItem(
                    icon = Icons.bullseye,
                    text = StringRes.str_median_score_formatted.asTextSource(author.medianScore.toString())
                ).takeIf { author.medianScore > 0 },
                IconTextItem(
                    icon = Icons.thumbUp,
                    text = StringRes.str_games_recommended_formatted.asTextSource(author.percentRecommended.toInt().toString())
                ).takeIf { author.percentRecommended > 0 }
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
            isFavoritesGamesVisible = author.favoriteGames.isNotEmpty(),
            favoritesGamesTitleText = StringRes.str_favorite_games.asTextSource(),
            favoritesGames = author.favoriteGames,
            personalInfoItems = listOfNotNull(
                IconTextItem(
                    icon = Icons.home,
                    text = author.hometown.asTextSource()
                ).takeIf { author.hometown.isNotBlank() },
                IconTextItem(
                    icon = Icons.xbox,
                    text = author.xboxLive.asTextSource()
                ).takeIf { author.xboxLive.isNotBlank() },
                IconTextItem(
                    icon = Icons.playstation,
                    text = author.psn.asTextSource()
                ).takeIf { author.psn.isNotBlank() },
            )
        )

    private fun loadMore() {
        if (!canLoadMore)
            return

        val state = requireNotNull(state.value as? AuthorReviewsState.Content)

        scope.launch {
            getAuthorReviewsInteractor(args.authorId, state.reviewItems.size, sorting)
                .onSuccess { reviews ->
                    state.copy(
                        reviewItems = state.reviewItems + reviews.map { review ->
                            ReviewListItem(review)
                        },
                        isLoadingItemVisible = state.reviewItems.size + reviews.size < (author?.reviewCount ?: 0)
                    ).let { content ->
                        mutableState.update { content }
                    }
                }
        }
    }

    private fun ReviewListItem(review: Review): ReviewListItem =
        ReviewListItem(
            review = review,
            isGameVisible = true,
            onClick = { openUrl(review.externalUrl) },
            onAuthorClick = {},
            onImageClick = {},
            onOutletClick = {},
            onGameClick = { openGame(review.gameId, review.gameName) },
        )

    private fun onSortSelected(item: ReviewSortItem) {
        if (item.key == sorting)
            return

        sorting = item.key
        canLoadMore = true

        val state = requireNotNull(state.value as? AuthorReviewsState.Content)

        mutableState.tryEmit(
            state.copy(
                reviewItems = emptyList(),
                isLoadingItemVisible = true
            )
        )

        loadMore()
    }

    private fun openUrl(url: String) {
        UrlRoute.navigate(url.asUrlRouteArgs())
    }

    private fun openGame(gameId: Long, gameName: String) {
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(gameId, gameName)
        )
    }
}