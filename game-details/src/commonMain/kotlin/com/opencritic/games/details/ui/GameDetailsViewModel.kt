package com.opencritic.games.details.ui

import com.opencritic.game.your.domain.SaveYourGameInteractor
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.game.your.ui.YourGameIndicatorItem
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.Trailer
import com.opencritic.games.details.domain.interactor.GetGameDetailsInteractor
import com.opencritic.games.roundScore
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.GameMediaRoute
import com.opencritic.navigation.GameReviewsRoute
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.images.SharedImages
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GameDetailsViewModel(
    private val gameId: Long,
    private val gameName: String,
    private val getGameDetailsInteractor: GetGameDetailsInteractor,
    private val saveYourGameInteractor: SaveYourGameInteractor,
    private val logger: Logger,
) : BaseViewModel<GameDetailsState>() {
    override fun initialState(): GameDetailsState = GameDetailsState.Loading(gameName)

    private var yourGame: YourGame? = null

    override fun onStateInit() {
        super.onStateInit()

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
        YourGameIndicatorItem(game) {
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
                GameReviewsRoute(gameId, gameName)
            )
    }

    private fun openTrailer(trailer: Trailer) {
        requireRouter()
            .navigateTo(
                UrlRoute(trailer.externalUrl)
            )
    }
}