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
import com.opencritic.resources.StringRes
import com.opencritic.resources.getFormattedString
import com.opencritic.resources.getString
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorReviewsViewModel(
    private val authorId: Int,
    private val authorName: String,
    private val getAuthorInteractor: GetAuthorInteractor,
    private val getAuthorReviewsInteractor: GetAuthorReviewsInteractor,
    private val stringProvider: StringProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<AuthorReviewsState>() {
    override fun initialState(): AuthorReviewsState =
        AuthorReviewsState.Loading(stringProvider.getFormattedString(StringRes.str_reviews_of, authorName))

    private var author: Author? = null
    private var canLoadMore: Boolean = true
    private var sorting: ReviewSorting = ReviewSorting.Default

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getAuthorInteractor(authorId)
                .onFailure {
                    mutableState.update {
                        AuthorReviewsState.Error(
                            stringProvider.getFormattedString(StringRes.str_reviews_of, authorName),
                            it.toString()
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
            titleText = stringProvider.getFormattedString(StringRes.str_reviews_of, author.name),
            nameText = author.name,
            bioText =
                if (author.isClaimed) author.bio
                else stringProvider.getFormattedString(StringRes.str_author_is_not_claimed, author.name),
            isBioVisible = !author.isClaimed || author.bio.isNotBlank(),
            imageUrl = author.imageUrl,
            sortTitleText = stringProvider.getString(StringRes.str_sort),
            countersInfoItems = listOfNotNull(
                IconTextItem(
                    icon = imageResourceProvider.hashTag,
                    text = stringProvider.getFormattedString(StringRes.str_games_reviewed_formatted, author.reviewCount.toString()),
                ).takeIf { author.reviewCount > 0 },
                IconTextItem(
                    icon = imageResourceProvider.chartPie,
                    text = stringProvider.getFormattedString(StringRes.str_average_score_formatted, author.averageScore.toInt().toString()),
                ).takeIf { author.averageScore > 0 },
                IconTextItem(
                    icon = imageResourceProvider.bullseye,
                    text = stringProvider.getFormattedString(StringRes.str_median_score_formatted, author.medianScore.toString())
                ).takeIf { author.medianScore > 0 },
                IconTextItem(
                    icon = imageResourceProvider.thumbUp,
                    text = stringProvider.getFormattedString(StringRes.str_games_recommended_formatted, author.percentRecommended.toInt().toString())
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
            favoritesGamesTitleText = stringProvider.getString(StringRes.str_favorite_games),
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
            stringProvider = stringProvider,
            dateFormatter = dateFormatter,
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
        requireRouter()
            .navigateTo(UrlRoute(url))
    }

    private fun openGame(gameId: Long, gameName: String) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(gameId, gameName)
            )
    }
}