package com.opencritic.games.details.ui

import com.opencritic.auth.api.domain.GetAuthStateInteractor
import com.opencritic.auth.api.ui.AuthRoute
import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GameListAction
import com.opencritic.game.your.domain.GameListId
import com.opencritic.game.your.domain.UpdateGameListInteractor
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.game.your.ui.lists.YourGameIndicatorItem
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.details.api.ui.GameMediaRoute
import com.opencritic.games.details.api.ui.GameReviewsRoute
import com.opencritic.games.details.api.ui.RatingReviewsRoute
import com.opencritic.games.details.domain.GameDetails
import com.opencritic.games.details.domain.GameRating
import com.opencritic.games.details.domain.interactor.GetGameDetailsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.ShareLinkRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.navigation.asShareLinkRouteArgs
import com.opencritic.navigation.asUrlRouteArgs
import com.opencritic.remote.images.ImagePreloader
import com.opencritic.remote.images.load
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.Format
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.formatDate
import kotlinx.collections.immutable.toImmutableList
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
    private val imagePreloader: ImagePreloader,
) : BaseContentViewModel<GameDetailsContent>() {
    override fun initialState(): CommonViewModelState<GameDetailsContent> =
        CommonViewModelState.loading(args.gameName.asTextSource())

    private var details: GameDetails? = null
    private var yourGame: YourGame? = null
    private var gameUrl: String? = null

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
                    this@GameDetailsViewModel.details = details
                    yourGame = details.yourGame
                    gameUrl = details.url

                    imagePreloader.load(details)

                    mutableState.update { state ->
                        state.content(
                            title = args.gameName.asTextSource(),
                            content = GameDetailsContent(
                                squareImageUrl = details.squareUrl,
                                bannerImageUrl = details.bannerUrl,
                                name = details.name,
                                yourGameIndicatorItem = createYourGameIndicatorItem(details.yourGame, details),
                                creatorsText = ("Creators: " + details.companies.joinToString(", ") { it.name }).asTextSource(),
                                platformsText = ("Platforms: " + details.platforms.joinToString(", ") { it.name }).asTextSource(),
                                releaseText = ("Release date: " + details.releaseDate.toLocalDateTime(TimeZone.UTC).date.formatDate(Format.Medium)).asTextSource(),
                                isTierVisible = details.rank != null,
                                tierImageResource = when (details.rank?.tier) {
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
                                playerRating = createPlayerRatingIndicator(details.rank?.tier ?: Tier.Weak, details.gameRating),
                                playerRatingDescription =
                                    if (details.gameRating.isCalculated) StringRes.str_game_rating.asTextSource()
                                    else StringRes.str_game_rating_unavailable_formatted.asTextSource(GameRating.minCount),
                                briefReviews = details.reviews
                                    .map { review ->
                                        ReviewBriefListItem(
                                            name = review.outlet.name,
                                            score = review.score,
                                            scoreFormat = review.scoreFormat,
                                        )
                                    }.toImmutableList(),
                                isViewAllVisible = details.reviewCount != 0,
                                viewAllText = StringRes.str_view_all_reviews.asTextSource(details.reviewCount.toString()),
                                isMediaVisible = details.trailers.size <= 1 && details.screenshotUrls.isNotEmpty(),
                                mediaText = StringRes.str_game_media.asTextSource(details.name),
                                media = (details.trailers
                                    .map {  trailer ->
                                        TrailerItem(trailer, ::openTrailer)
                                    } + details.screenshotUrls
                                    .take(3)
                                    .map { ScreenshotItem(it) }
                                    )
                                    .toImmutableList(),
                                viewAllMedia = StringRes.str_view_all_media.asTextSource(),
                                isTrailersVisible = details.trailers.size > 1,
                                trailersText = StringRes.str_game_trailers.asTextSource(details.name),
                                trailers = details.trailers
                                    .take(3)
                                    .map { trailer ->
                                        TrailerItem(trailer, ::openTrailer)
                                    }
                                    .toImmutableList(),
                                viewAllTrailers = StringRes.str_view_all_trailers.asTextSource(),
                                isScreenshotsVisible = details.screenshotUrls.isNotEmpty() && details.trailers.size > 1,
                                screenshotsText = StringRes.str_game_screenshots.asTextSource(details.name),
                                screenshots = details.screenshotUrls
                                    .take(3)
                                    .map { ScreenshotItem(it) }
                                    .toImmutableList(),
                                viewAllScreenshots = StringRes.str_view_all_screenshots.asTextSource(),
                                isReviewsVisible = details.reviewCount != 0,
                                reviewTitleText = StringRes.str_critic_reviews_for_formatted.asTextSource(details.name),
                                reviews = details.reviews
                                    .take(8)
                                    .map { review ->
                                        CardReviewItem(
                                            review = review,
                                            readFullReviewText = StringRes.str_read_full_review.asTextSource(),
                                            onClick = ::onCardReviewItemClick,
                                        )
                                    }
                                    .toImmutableList(),
                                onViewAllMediaClick = ::openMedia,
                                onViewAllScreenshotsClick = ::openMedia,
                                onViewAllTrailersClick = ::openMedia,
                                onViewAllReviewsClick = ::openReviews,
                                onGameRatingClick = ::onGameRatingClick,
                                onRefresh = ::onRefresh,
                                isActionVisible = true,
                                actionIconResource = Icons.share,
                                onAction = ::shareGameUrl
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

    private fun onGameRatingClick() {
        val rating = details?.gameRating ?: return
        if (!rating.isCalculated) return

        RatingReviewsRoute.navigate(
            RatingReviewsRoute.InitArgs(
                gameId = args.gameId,
                gameName = args.gameName,
            )
        )
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

    private fun shareGameUrl() {
        val gameUrl = gameUrl ?: return

        ShareLinkRoute.navigate(gameUrl.asShareLinkRouteArgs())
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

    private fun onCardReviewItemClick(item: CardReviewItem) =
        UrlRoute.navigate(item.externalUrl.asUrlRouteArgs())

    private fun openTrailer(trailer: TrailerItem) =
        UrlRoute.navigate(trailer.externalUrl.asUrlRouteArgs())
}