package com.opencritic.games.details.domain.interactor

import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.GameRatingReview
import com.opencritic.games.details.domain.RatingReviewSorting
import com.opencritic.games.details.domain.RatingTimeframe

class GetGameRatingReviewsInteractor(
    private val gameDetailsRepository: GameDetailsRepository,
) {
    suspend operator fun invoke(
        gameId: Long,
        page: Int,
        limit: Int,
        sorting: RatingReviewSorting,
        timeframe: RatingTimeframe,
    ): Result<List<GameRatingReview>> =
        runCatching {
            gameDetailsRepository.getGameRatingReviews(gameId, page, limit, sorting, timeframe)
        }
}