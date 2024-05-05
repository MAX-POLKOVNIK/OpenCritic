package com.opencritic.game.your.data

import com.opencritic.database.YourGameDao
import com.opencritic.database.YourGameEntity
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class YourGameRepositoryImpl(
    private val yourGameDao: YourGameDao,
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

    private fun YourGame(entity: YourGameEntity): YourGame =
        YourGame(
            id = entity.id,
            name = entity.name,
            isWanted = entity.isWanted,
            isPlayed = entity.isPlayed,
            isFavorite = entity.isFavorite
        )

    private fun YourGameEntity(model: YourGame): YourGameEntity =
        YourGameEntity()
            .apply {
                id = model.id
                name = model.name
                isWanted = model.isWanted
                isPlayed = model.isPlayed
                isFavorite = model.isFavorite
                isInterested = model.isInterested
            }
}