package com.opencritic.game.your.domain

import com.opencritic.game.your.ui.YourGameFilter

class GetYourGameListInteractor(
    private val repository: YourGameRepository,
) {
    suspend operator fun invoke(filter: YourGameFilter) : Result<List<YourGame>> =
        runCatching {
            repository.remoteNotInterested()

            when (filter) {
                YourGameFilter.All -> repository.getAll()
                YourGameFilter.Wanted -> repository.getWanted()
                YourGameFilter.Played -> repository.getPlayed()
                YourGameFilter.Favorite -> repository.getFavorites()
            }
        }
}