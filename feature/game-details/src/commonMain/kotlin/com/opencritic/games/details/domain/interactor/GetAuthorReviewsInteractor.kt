package com.opencritic.games.details.domain.interactor

import com.opencritic.games.Review
import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.ReviewSorting

class GetAuthorReviewsInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(authorId: Int, skip: Int, sort: ReviewSorting): Result<List<Review>> =
        runCatching { repository.getAuthorReviews(authorId, skip, sort) }
}