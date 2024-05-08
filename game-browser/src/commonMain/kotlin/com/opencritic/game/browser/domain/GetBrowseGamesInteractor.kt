package com.opencritic.game.browser.domain

class GetBrowseGamesInteractor(
    private val repository: GameBrowserRepository,
) {
    suspend operator fun invoke(
        platformCode: String,
        time: GameTimeframe,
        sorting: GameSorting,
        skip: Int,
    ): Result<List<BrowseGame>> =
        runCatching { repository.getGames(platformCode, time, sorting, skip) }
}