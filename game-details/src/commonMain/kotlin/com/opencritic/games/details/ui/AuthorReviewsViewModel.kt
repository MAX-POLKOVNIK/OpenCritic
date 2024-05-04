package com.opencritic.games.details.ui

import com.opencritic.games.Review
import com.opencritic.games.details.domain.Author
import com.opencritic.games.details.domain.ReviewSorting
import com.opencritic.games.details.domain.interactor.GetAuthorInteractor
import com.opencritic.games.details.domain.interactor.GetAuthorReviewsInteractor
import com.opencritic.games.details.domain.sortNameOf
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch

class AuthorReviewsViewModel(
    private val authorId: Int,
    private val getAuthorInteractor: GetAuthorInteractor,
    private val getAuthorReviewsInteractor: GetAuthorReviewsInteractor,
    private val stringProvider: StringProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<AuthorReviewsState>() {
    override val initialState: AuthorReviewsState =
        AuthorReviewsState.Loading

    private var author: Author? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    init {
        scope.launch {
            getAuthorInteractor(authorId)
                .onFailure {
                    mutableState.tryEmit(
                        AuthorReviewsState.Error(it.toString())
                    )
                }
                .onSuccess {
                    mutableState.tryEmit(
                        createContentState(it)
                    )

                    author = it
                }

            if (author != null) {
                getAuthorReviewsInteractor(authorId, skip = 0, sort = sorting)
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
                            .let {
                                mutableState.tryEmit(it)
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
            titleText = stringProvider.reviewsOf(author.name),
            nameText = author.name,
            bioText =
                if (author.isClaimed) author.bio
                else stringProvider.authorIsNotClaimedFormatted(author.name),
            isBioVisible = !author.isClaimed || author.bio.isNotBlank(),
            imageUrl = author.imageUrl,
            sortTitleText = stringProvider.sort,
            countersInfoItems = listOfNotNull(
                IconTextItem(
                    icon = imageResourceProvider.hashTag,
                    text = stringProvider.gamesReviewedFormatted(author.reviewCount.toString()),
                ).takeIf { author.reviewCount > 0 },
                IconTextItem(
                    icon = imageResourceProvider.chartPie,
                    text = stringProvider.averageScoreFormatted(author.averageScore.toInt().toString()),
                ).takeIf { author.averageScore > 0 },
                IconTextItem(
                    icon = imageResourceProvider.bullseye,
                    text = stringProvider.medianScoreFormatted(author.medianScore.toString())
                ).takeIf { author.medianScore > 0 },
                IconTextItem(
                    icon = imageResourceProvider.thumbUp,
                    text = stringProvider.gamesRecommendedFormatted(author.percentRecommended.toInt().toString())
                ).takeIf { author.percentRecommended > 0 }
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
            isFavoritesGamesVisible = author.favoriteGames.isNotEmpty(),
            favoritesGamesTitleText = stringProvider.favoriteGames,
            favoritesGames = author.favoriteGames,
            personalInfoItems = listOfNotNull(
                IconTextItem(
                    icon = imageResourceProvider.home,
                    text = author.hometown
                ).takeIf { author.hometown.isNotBlank() },
                IconTextItem(
                    icon = imageResourceProvider.xbox,
                    text = author.xboxLive
                ).takeIf { author.xboxLive.isNotBlank() },
                IconTextItem(
                    icon = imageResourceProvider.playstation,
                    text = author.psn
                ).takeIf { author.psn.isNotBlank() },
            )
        )

    private fun loadMore() {
        if (!canLoadMore)
            return

        val state = requireNotNull(state.value as? AuthorReviewsState.Content)

        scope.launch {
            getAuthorReviewsInteractor(authorId, state.reviewItems.size, sorting)
                .onSuccess { reviews ->
                    state.copy(
                        reviewItems = state.reviewItems + reviews.map { review ->
                            ReviewListItem(review)
                        },
                        isLoadingItemVisible = state.reviewItems.size + reviews.size < (author?.reviewCount ?: 0)
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
            onAuthorClick = {},
            onImageClick = {},
            onOutletClick = {},
            onGameClick = { openGame(review.gameId) },
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
        requireRouter()
            .navigateTo(UrlRoute(url))
    }

    private fun openGame(gameId: Long) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId)
            )
    }
}