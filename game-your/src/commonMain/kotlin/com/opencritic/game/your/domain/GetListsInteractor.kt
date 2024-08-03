package com.opencritic.game.your.domain

class GetListsInteractor(
    private val yourGameRepository: YourGameRepository
) {
    suspend operator fun invoke(): Result<List<GameList>> =
        runCatching { yourGameRepository.getLists() }
}