package com.opencritic.game.your.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.api.dto.list.GameListDto
import com.opencritic.auth.domain.GetAuthStateInteractor
import com.opencritic.database.YourGameDao
import com.opencritic.database.YourGameEntity
import com.opencritic.game.your.domain.GameList
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class YourGameRepositoryImpl(
    private val yourGameDao: YourGameDao,
    private val openCriticApi: OpenCriticsApi,
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : YourGameRepository {
    override suspend fun get(gameId: Long, gameName: String): YourGame =
        withContext(defaultDispatcher) {
            yourGameDao.get(gameId)
                ?.let { YourGame(it) }
                ?: YourGame(
                    id = gameId,
                    name = gameName,
                    isFavorite = false,
                    isPlayed = false,
                    isWanted = false,
                )
        }

    override suspend fun update(game: YourGame) =
        withContext(defaultDispatcher) {
            yourGameDao.insert(
                YourGameEntity(game)
            )
        }

    override suspend fun remoteNotInterested() =
        withContext(defaultDispatcher) {
            yourGameDao.removeNotInterested()
        }

    override suspend fun getAll(): List<YourGame> =
        withContext(defaultDispatcher) {
            yourGameDao.getAll()
                .map { YourGame(it) }
        }

    override suspend fun getWanted(): List<YourGame> =
        withContext(defaultDispatcher) {
            yourGameDao.getWanted()
                .map { YourGame(it) }
        }

    override suspend fun getPlayed(): List<YourGame> =
        withContext(defaultDispatcher) {
            yourGameDao.getPlayed()
                .map { YourGame(it) }
        }

    override suspend fun getFavorites(): List<YourGame> =
        withContext(defaultDispatcher) {
            yourGameDao.getFavorites()
                .map { YourGame(it) }
        }

    override suspend fun getLists(): List<GameList> =
        withContext(defaultDispatcher) {
            val token = getAuthStateInteractor().getOrThrow().authToken ?: throw Exception("No token")

            openCriticApi.getLists(token)
                .map { GameList(it) }
        }

    private fun GameList(dto: GameListDto): GameList =
        GameList(
            id = dto.id,
            posters = dto.gamesPopulated.map { it.images.box?.sm?.prefixedImageUrl() ?: "" },
            name = dto.label,
            gamesCount = dto.numGames,
            shareLink = "https://opencritic.com/list/${dto.id}"
        )

    private fun YourGame(entity: YourGameEntity): YourGame =
        YourGame(
            id = entity.id,
            name = entity.name,
            isWanted = entity.isWanted,
            isPlayed = entity.isPlayed,
            isFavorite = entity.isFavorite
        )

    private fun YourGameEntity(model: YourGame): YourGameEntity =
        YourGameEntity(
            id = model.id,
            name = model.name,
            isWanted = model.isWanted,
            isPlayed = model.isPlayed,
            isFavorite = model.isFavorite,
            isInterested = model.isInterested,
        )
}