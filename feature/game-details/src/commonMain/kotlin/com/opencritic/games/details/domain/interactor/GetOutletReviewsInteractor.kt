package com.opencritic.games.details.domain.interactor

import com.opencritic.games.Review
import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.ReviewSorting

class GetOutletReviewsInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(outletId: Int, skip: Int, sort: ReviewSorting): Result<List<Review>> =
        runCatching {
            repository.getOutletReviews(outletId, skip, sort)
        }
}