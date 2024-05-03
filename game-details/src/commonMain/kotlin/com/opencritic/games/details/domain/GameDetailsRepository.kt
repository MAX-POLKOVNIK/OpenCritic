package com.opencritic.games.details.domain

import com.opencritic.games.Game
import com.opencritic.games.Review

interface GameDetailsRepository {
    suspend fun getGame(gameId: Long): Game
    suspend fun getReviews(gameId: Long, skip: Int = 0): List<Review>
}