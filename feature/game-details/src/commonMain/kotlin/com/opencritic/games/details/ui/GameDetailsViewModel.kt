package com.opencritic.games.details.ui

import com.opencritic.auth.api.ui.AuthRoute
import com.opencritic.auth.api.domain.GetAuthStateInteractor
import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GameListAction
import com.opencritic.game.your.domain.GameListId
import com.opencritic.game.your.domain.UpdateGameListInteractor
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.game.your.ui.lists.YourGameIndicatorItem
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.Trailer
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.details.api.ui.GameMediaRoute
import com.opencritic.games.details.api.ui.GameReviewsRoute
import com.opencritic.games.details.domain.GameDetails
import com.opencritic.games.details.domain.interactor.GetGameDetailsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.ShareLinkRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.navigation.asShareLinkRouteArgs
import com.opencritic.navigation.asUrlRouteArgs
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GameDetailsViewModel(
    private val args: GameDetailsRoute.InitArgs,
    private val getGameDetailsInteractor: GetGameDetailsInteractor,
    private val updateGameListInteractor: UpdateGameListInteractor,
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val logger: Logger,
) : BaseContentViewModel<GameDetailsContent>() {
    override fun initialState(): CommonViewModelState<GameDetailsContent> =
        CommonViewModelState.loading(args.gameName.asTextSource())

    private var yourGame: YourGame? = null

    override fun onStateInit() {
        super.onStateInit()

        loadGame()
    }

    private fun loadGame() {
        scope.launch {
            showLoading()

            getGameDetailsInteractor(args.gameId)
                .onFailure {
                    showError(it) {
                        loadGame()
                    }

                    logger.log(it.toString())
                }
                .onSuccess { details ->
                    yourGame = details.yourGame

                    mutableState.update { state ->
                        state.content(
                            title = args.gameName.asTextSource(),
                            content = GameDetailsContent(
                                isImageVisible = details.squareUrl.isNotBlank(),
                                imageUrl = details.squareUrl,
                                bannerImageUrl = details.bannerUrl,
                                name = details.name,
                                yourGameIndicatorItem = createYourGameIndicatorItem(details.yourGame, details),
                                companiesText = details.companies.joinToString(", ") { it.name },
                                releaseDateText = details.releaseDate.toLocalDateTime(TimeZone.UTC).date format DateTextSource.Format.Medium,
                                platformsText = details.platforms.joinToString(", ") { it.name },
                                isTierVisible = details.rank != null,
                                tierImageResource = when(details.rank?.tier) {
                                    Tier.Mighty -> SharedImages.mightyMan
                                    Tier.Strong -> SharedImages.strongMan
                                    Tier.Fair -> SharedImages.fairMan
                                    Tier.Weak -> SharedImages.weakMan
                                    null -> SharedImages.weakMan
                                },
                                tier = details.rank?.tier,
                                tierDescription = StringRes.str_open_critic_rating.asTextSource(),
                                topCriticScore = createTopCriticAverageIndicator(
                                    gameRank = details.rank ?: GameRank(Tier.Weak, 0)
                                ),
                                topCriticScoreDescription = StringRes.str_top_critic_average.asTextSource(),
                                recommendedPercent = createCriticsRecommendIndicator(
                                    tier = details.rank?.tier ?: Tier.Weak,
                                    score = details.recommendPercent ?: 0f
                                ),
                                criticsRecommendDescription = StringRes.str_critics_recommend.asTextSource(),
                                briefReviews = details.reviews
                                    .map { review ->
                                        ReviewBriefListItem(
                                            name = review.outlet.name,
                                            score = review.score,
                                            scoreFormat = review.scoreFormat,
                                        )
                                    },
                                isViewAllVisible = details.reviewCount != 0,
                                viewAllText = StringRes.str_view_all_reviews.asTextSource(details.reviewCount.toString()),
                                isMediaVisible = details.trailers.size <= 1 && details.screenshotUrls.isNotEmpty(),
                                mediaText = StringRes.str_game_media.asTextSource(details.name),
                                media = details.trailers
                                    .map {  trailer ->
                                        TrailerItem(trailer) { openTrailer(trailer) }
                                    } + details.screenshotUrls
                                    .take(3)
                                    .map {
                                        ScreenshotItem(it) {}
                                    },
                                viewAllMedia = StringRes.str_view_all_media.asTextSource(),
                                isTrailersVisible = details.trailers.size > 1,
                                trailersText = StringRes.str_game_trailers.asTextSource(details.name),
                                trailers = details.trailers
                                    .take(3)
                                    .map { trailer ->
                                        TrailerItem(trailer) { openTrailer(trailer) }
                                    },
                                viewAllTrailers = StringRes.str_view_all_trailers.asTextSource(),
                                isScreenshotsVisible = details.screenshotUrls.isNotEmpty() && details.trailers.size > 1,
                                screenshotsText = StringRes.str_game_screenshots.asTextSource(details.name),
                                screenshots = details.screenshotUrls.take(3).map { ScreenshotItem(it) {} },
                                viewAllScreenshots = StringRes.str_view_all_screenshots.asTextSource(),
                                isReviewsVisible = details.reviewCount != 0,
                                reviewTitleText = StringRes.str_critic_reviews_for_formatted.asTextSource(details.name),
                                reviews = details.reviews
                                    .take(8)
                                    .map { review ->
                                        CardReviewItem(
                                            review = review,
                                            readFullReviewText = StringRes.str_read_full_review.asTextSource(),
                                            onClick = {
                                                UrlRoute.navigate(review.externalUrl.asUrlRouteArgs())
                                            },
                                        )
                                    },
                                onViewAllMediaClick = { openMedia() },
                                onViewAllScreenshotsClick = { openMedia() },
                                onViewAllTrailersClick = { openMedia() },
                                onViewAllReviewsClick = { openReviews() },
                                onRefresh = ::onRefresh,
                                isActionVisible = true,
                                actionIconResource = Icons.share,
                                onAction = { shareGameUrl(details.url) }
                            )
                        )
                    }
                }
        }
    }

    private fun onRefresh() {
        scope.launch {
            getGameDetailsInteractor(args.gameId)
                .onSuccess { details ->
                    yourGame = details.yourGame

                    updateContentIfSet {
                        copy(
                            yourGameIndicatorItem = createYourGameIndicatorItem(details.yourGame, details)
                        )
                    }
                }
        }
    }

    private fun createYourGameIndicatorItem(yourGame: YourGame, game: GameDetails): YourGameIndicatorItem =
        YourGameIndicatorItem(yourGame) {
            onGameAction(it, game)
        }

    private fun onGameAction(action: YourGameAction, game: GameDetails) {
        scope.launch {
            val auth = getAuthStateInteractor()

            if (auth.isFailure) {
                return@launch
            }

            if (auth.getOrThrow().shouldAskToLogin) {
                AuthRoute.navigate(AuthRoute.InitArgs)
                return@launch
            }

            val yourGame = yourGame ?: return@launch

            val new = yourGame.actioned(action)
            val indicator = createYourGameIndicatorItem(new, game)

            this@GameDetailsViewModel.yourGame = new

            mutableState.update {
                it.updateContent {
                    copy(
                        yourGameIndicatorItem = indicator
                    )
                }
            }

            val (list, act) = when (action) {
                YourGameAction.Want -> GameListId.Want to if (new.isWanted) GameListAction.Add else GameListAction.Remove
                YourGameAction.Played -> GameListId.Played to if (new.isPlayed) GameListAction.Add else GameListAction.Remove
                YourGameAction.Favorite -> GameListId.Favorite to if (new.isFavorite) GameListAction.Add else GameListAction.Remove
            }

            val gameInList = GameInList(
                id = game.id,
                name = game.name,
                posterUrl = game.posterUrl,
                rank = game.rank,
            )

            updateGameListInteractor(
                gameListId = list,
                action = act,
                game = gameInList,
            ).onFailure {
                logger.log("Error add something: $it")
            }
        }
    }

    private fun shareGameUrl(url: String) {
        ShareLinkRoute.navigate(url.asShareLinkRouteArgs())
    }

    private fun openMedia() {
        GameMediaRoute.navigate(
            GameMediaRoute.InitArgs(
                gameId = args.gameId,
                gameName = args.gameName,
            )
        )
    }

    private fun openReviews() {
        GameReviewsRoute.navigate(
            GameReviewsRoute.InitArgs(
                gameId = args.gameId,
                gameName = args.gameName,
            )
        )
    }

    private fun openTrailer(trailer: Trailer) {
        UrlRoute.navigate(trailer.externalUrl.asUrlRouteArgs())
    }
}