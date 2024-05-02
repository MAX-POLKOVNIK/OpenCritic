package com.opencritic.games.details.ui

import com.opencritic.games.Tier
import com.opencritic.games.details.domain.GetGameDetailsInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GameDetailsViewModel(
    private val gameId: Long,
    private val getGameDetailsInteractor: GetGameDetailsInteractor,
    private val stringResourceProvider: StringResourceProvider,
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
                                stringResourceProvider = stringResourceProvider,
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
                            tierDescription = stringResourceProvider.openCriticRating.get(stringResourceProvider),
                            topCriticScore = details.rank?.score,
                            topCriticScoreDescription = stringResourceProvider.topCriticAverage.get(stringResourceProvider),
                            recommendedPercent = details.recommendPercent,
                            criticsRecommendDescription = stringResourceProvider.criticsRecommend.get(stringResourceProvider),
                            briefReviews = details.reviews.map { review ->
                                ReviewBriefListItem(
                                    name = review.outlet.name,
                                    score = review.score,
                                    scoreFormat = review.scoreFormat,
                                )
                            },
                            isViewAllVisible = details.reviewCount != 0,
                            viewAllText = stringResourceProvider.viewAllReviewsFormatted.get(stringResourceProvider, details.reviewCount.toString()),
                            isMediaVisible = details.trailers.size <= 1 && details.screenshotUrls.isNotEmpty(),
                            mediaText = "${details.name} ${stringResourceProvider.media.get(stringResourceProvider)}",
                            media = details.trailers.map { TrailerItem(it, {}) } + details.screenshotUrls.take(3).map { ScreenshotItem(it, {}) },
                            viewAllMedia = "${stringResourceProvider.viewAll.get(stringResourceProvider)} ${stringResourceProvider.media.get(stringResourceProvider)}",
                            isTrailersVisible = details.trailers.size > 1,
                            trailersText = "${details.name} ${stringResourceProvider.trailers.get(stringResourceProvider)}",
                            trailers = details.trailers.take(3).map { TrailerItem(it, {}) },
                            viewAllTrailers = "${stringResourceProvider.viewAll.get(stringResourceProvider)} ${stringResourceProvider.trailers.get(stringResourceProvider)}",
                            isScreenshotsVisible = details.screenshotUrls.isNotEmpty(),
                            screenshotsText = "${details.name} ${stringResourceProvider.screenshots.get(stringResourceProvider)}",
                            screenshots = details.screenshotUrls.take(3).map { ScreenshotItem(it, {}) },
                            viewAllScreenshots = "${stringResourceProvider.viewAll.get(stringResourceProvider)} ${stringResourceProvider.screenshots.get(stringResourceProvider)}",
                        )
                    )
                }
        }
    }
}