package com.opencritic.games.details.domain

import com.opencritic.games.Review

class GetGameReviewsInteractor(
    private val gameDetailsRepository: GameDetailsRepository,
) {
    suspend operator fun invoke(
        gameId: Long,
        skip: Int = 0,
        sorting: ReviewSorting = ReviewSorting.MostPopular
    ): Result<List<Review>> =
        runCatching {
            gameDetailsRepository.getReviews(gameId, skip, sorting)
        }
}