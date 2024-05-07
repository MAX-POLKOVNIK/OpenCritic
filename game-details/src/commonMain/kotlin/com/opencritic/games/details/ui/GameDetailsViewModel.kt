package com.opencritic.games.details.ui

import com.opencritic.game.your.domain.SaveYourGameInteractor
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.game.your.ui.YourGameIndicatorItem
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.Trailer
import com.opencritic.games.details.domain.interactor.GetGameDetailsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.GameMediaRoute
import com.opencritic.navigation.GameReviewsRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GameDetailsViewModel(
    private val gameId: Long,
    private val gameName: String,
    private val getGameDetailsInteractor: GetGameDetailsInteractor,
    private val saveYourGameInteractor: SaveYourGameInteractor,
    private val stringProvider: StringProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<GameDetailsState>() {
    override val initialState: GameDetailsState
        get() = GameDetailsState.Loading(gameName)

    private var yourGame: YourGame? = null

    init {
        scope.launch {
            getGameDetailsInteractor(gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameDetailsState.Error(
                            titleText = gameName,
                            message = it.toString()
                        )
                    )
                    logger.log(it.toString())
                }
                .onSuccess { details ->
                    yourGame = details.yourGame

                    mutableState.tryEmit(
                        GameDetailsState.Content(
                            isImageVisible = details.posterUrl.isNotBlank(),
                            imageUrl = details.posterUrl,
                            name = details.name,
                            yourGameIndicatorItem = createYourGameIndicatorItem(details.yourGame),
                            companiesText = details.companies.joinToString(", ") { it.name },
                            releaseDateText = dateFormatter.format(
                                details.releaseDate.toLocalDateTime(TimeZone.UTC).date
                            ),
                            platformsText = details.platforms.joinToString(", ") { it.name },
                            isTierVisible = details.rank != null,
                            tierImageResource = when(details.rank?.tier) {
                                Tier.Mighty -> imageResourceProvider.mightyMan
                                Tier.Strong -> imageResourceProvider.strongMan
                                Tier.Fair -> imageResourceProvider.fairMan
                                Tier.Weak -> imageResourceProvider.weakMan
                                null -> imageResourceProvider.weakMan
                            },
                            tier = details.rank?.tier,
                            tierDescription = stringProvider.openCriticRating,
                            topCriticScore = createTopCriticAverageIndicator(details.rank ?: GameRank(Tier.Weak, 0)),
                            topCriticScoreDescription = stringProvider.topCriticAverage,
                            recommendedPercent = createCriticsRecommendIndicator(details.rank?.tier ?: Tier.Weak, details.recommendPercent ?: 0),
                            criticsRecommendDescription = stringProvider.criticsRecommend,
                            briefReviews = details.reviews
                                .filter { it.score != null }
                                .filterNot { it.scoreFormat.isSelect }
                                .take(8)
                                .map { review ->
                                    ReviewBriefListItem(
                                        name = review.outlet.name,
                                        score = review.score?.toInt(),
                                        scoreFormat = review.scoreFormat,
                                    )
                                },
                            isViewAllVisible = details.reviewCount != 0,
                            viewAllText = stringProvider.viewAllReviewsFormatted(details.reviewCount.toString()),
                            isMediaVisible = details.trailers.size <= 1 && details.screenshotUrls.isNotEmpty(),
                            mediaText = "${details.name} ${stringProvider.media}",
                            media = details.trailers
                                .map {  trailer ->
                                    TrailerItem(trailer) { openTrailer(trailer) }
                                } + details.screenshotUrls
                                    .take(3)
                                    .map {
                                        ScreenshotItem(it) {}
                                    },
                            viewAllMedia = "${stringProvider.viewAll} ${stringProvider.media}",
                            isTrailersVisible = details.trailers.size > 1,
                            trailersText = "${details.name} ${stringProvider.trailers}",
                            trailers = details.trailers
                                .take(3)
                                .map { trailer ->
                                    TrailerItem(trailer) { openTrailer(trailer) }
                                },
                            viewAllTrailers = "${stringProvider.viewAll} ${stringProvider.trailers}",
                            isScreenshotsVisible = details.screenshotUrls.isNotEmpty() && details.trailers.size > 1,
                            screenshotsText = "${details.name} ${stringProvider.screenshots}",
                            screenshots = details.screenshotUrls.take(3).map { ScreenshotItem(it) {} },
                            viewAllScreenshots = "${stringProvider.viewAll} ${stringProvider.screenshots}",
                            isReviewsVisible = details.reviewCount != 0,
                            reviewTitleText = stringProvider.criticReviewsForFormatted(details.name),
                            reviews = details.reviews
                                .take(8)
                                .map { review ->
                                    CardReviewItem(
                                        review = review,
                                        readFullReviewText = stringProvider.readFullReview,
                                        onClick = {
                                            requireRouter().navigateTo(UrlRoute(review.externalUrl))
                                        },
                                    )
                                },
                            onViewAllMediaClick = { openMedia() },
                            onViewAllScreenshotsClick = { openMedia() },
                            onViewAllTrailersClick = { openMedia() },
                            onViewAllReviewsClick = { openReviews() },
                            titleText = gameName,
                        )
                    )
                }
        }
    }

    private fun createYourGameIndicatorItem(game: YourGame): YourGameIndicatorItem =
        YourGameIndicatorItem(game, stringProvider, imageResourceProvider) {
            onGameAction(it)
        }

    private fun onGameAction(action: YourGameAction) {
        val game = yourGame ?: return

        val state = requireNotNull(mutableState.value as? GameDetailsState.Content)

        val new = game.actioned(action)
        val indicator = createYourGameIndicatorItem(new)

        yourGame = new

        mutableState.tryEmit(
            state.copy(
                yourGameIndicatorItem = indicator
            )
        )

        scope.launch {
            saveYourGameInteractor(new)
                .onSuccess { logger.log("Updated: $new") }
                .onFailure { logger.log("FAILED UPDATE: $it $new") }
        }
    }

    private fun openMedia() {
        requireRouter()
            .navigateTo(
                GameMediaRoute(gameId, gameName)
            )
    }

    private fun openReviews() {
        requireRouter()
            .navigateTo(
                GameReviewsRoute(gameId)
            )
    }

    private fun openTrailer(trailer: Trailer) {
        requireRouter()
            .navigateTo(
                UrlRoute(trailer.externalUrl)
            )
    }
}