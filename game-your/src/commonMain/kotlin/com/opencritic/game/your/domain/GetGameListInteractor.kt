package com.opencritic.game.your.domain

class GetGameListInteractor(
    private val repository: YourGameRepository,
) {
    suspend operator fun invoke(listId: String): Result<GameList> =
        runCatching { repository.getList(listId) }
}