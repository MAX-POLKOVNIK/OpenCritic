package com.opencritic.games.details.ui

import com.opencritic.games.Tier
import com.opencritic.games.details.domain.GetGameDetailsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GameDetailsViewModel(
    private val gameId: Long,
    private val getGameDetailsInteractor: GetGameDetailsInteractor,
    private val stringProvider: StringProvider,
    private val imageResourceProvider: ImageResourceProvider,
    private val dateFormatter: DateFormatter,
    private val logger: Logger,
) : BaseViewModel<GameDetailsState>() {
    override val initialState: GameDetailsState
        get() = GameDetailsState.Loading

    init {
        scope.launch {
            getGameDetailsInteractor(gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameDetailsState.Error(
                            message = it.toString()
                        )
                    )
                    logger.log(it.toString())
                }
                .onSuccess { details ->
                    mutableState.tryEmit(
                        GameDetailsState.Content(
                            isImageVisible = details.posterUrl.isNotBlank(),
                            imageUrl = details.posterUrl,
                            name = details.name,
                            gameActionItems = gameActionItems(
                                imageResourceProvider = imageResourceProvider,
                                stringProvider = stringProvider,
                                isWanted = false,
                                isPlayed = false,
                                isFavorite = false,
                                onClick = {},
                            ),
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
                            topCriticScore = details.rank?.score,
                            topCriticScoreDescription = stringProvider.topCriticAverage,
                            recommendedPercent = details.recommendPercent,
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
                            media = details.trailers.map { TrailerItem(it, {}) } + details.screenshotUrls.take(3).map { ScreenshotItem(it, {}) },
                            viewAllMedia = "${stringProvider.viewAll} ${stringProvider.media}",
                            isTrailersVisible = details.trailers.size > 1,
                            trailersText = "${details.name} ${stringProvider.trailers}",
                            trailers = details.trailers
                                .take(3)
                                .map { trailer ->
                                    TrailerItem(trailer) {
                                        requireRouter().navigateTo(UrlRoute(trailer.externalUrl))
                                    }
                                },
                            viewAllTrailers = "${stringProvider.viewAll} ${stringProvider.trailers}",
                            isScreenshotsVisible = details.screenshotUrls.isNotEmpty() && details.trailers.size > 1,
                            screenshotsText = "${details.name} ${stringProvider.screenshots}",
                            screenshots = details.screenshotUrls.take(3).map { ScreenshotItem(it, {}) },
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
                                }
                        )
                    )
                }
        }
    }
}