package com.opencritic.games.details.domain

import com.opencritic.games.Game
import com.opencritic.games.Review

interface GameDetailsRepository {
    suspend fun getGame(gameId: Long, token: String?): Game

    suspend fun getGameMedia(gameId: Long): Game

    suspend fun getGameReviewsLanding(gameId: Long): List<Review>

    suspend fun getGameReviews(
        gameId: Long,
        skip: Int = 0,
        sort: ReviewSorting = ReviewSorting.MostPopular
    ): List<Review>

    suspend fun getOutlet(outletId: Int): Outlet

    suspend fun getOutletReviews(
        outletId: Int,
        skip: Int,
        sort: ReviewSorting,
    ): List<Review>

    suspend fun getAuthor(authorId: Int): Author

    suspend fun getAuthorReviews(
        authorId: Int,
        skip: Int,
        sort: ReviewSorting,
    ): List<Review>
}