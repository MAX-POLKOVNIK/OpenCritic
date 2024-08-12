package com.opencritic.game.browser.domain

import com.opencritic.games.Platform

interface GameBrowserRepository {
    suspend fun getPlatforms(): List<Platform>
    suspend fun getGames(
        platformCode: String,
        time: GameTimeframe,
        sorting: GameSorting,
        skip: Int,
        isExclusive: Boolean,
    ): List<BrowseGame>

    suspend fun getReviewedThisWeek(): List<BrowseGame>
}