package com.opencritic.games.details.domain.interactor

import com.opencritic.games.Review
import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.ReviewSorting

class GetGameReviewsInteractor(
    private val gameDetailsRepository: GameDetailsRepository,
) {
    suspend operator fun invoke(
        gameId: Long,
        skip: Int = 0,
        sorting: ReviewSorting = ReviewSorting.MostPopular
    ): Result<List<Review>> =
        runCatching {
            gameDetailsRepository.getGameReviews(gameId, skip, sorting)
        }
}