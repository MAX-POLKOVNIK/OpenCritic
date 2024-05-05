package com.opencritic.database

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

internal class YourGameDaoImpl(
    private val realm: Realm
) : YourGameDao {
    override suspend fun insert(game: YourGameEntity) {
        realm.write {
            val stored = query<YourGameEntity>("${YourGameEntity::id.name} = $0", game.id)
                .first()
                .find()

            stored?.apply {
                isWanted = game.isWanted
                isPlayed = game.isPlayed
                isFavorite = game.isFavorite
                isInterested = game.isInterested
            } ?: copyToRealm(game)
        }
    }

    override suspend fun get(id: Long): YourGameEntity? =
        realm.query<YourGameEntity>("${YourGameEntity::id.name} = $0", id)
            .first()
            .find()

    override suspend fun removeNotInterested() {
        realm.write {
            val toDelete = query<YourGameEntity>("${YourGameEntity::isInterested.name} = $0", false)
                .find()

            delete(toDelete)
        }
    }

    override suspend fun getAll(): List<YourGameEntity> =
        realm.query<YourGameEntity>()
            .find()
            .toList()

    override suspend fun getWanted(): List<YourGameEntity> =
        realm.query<YourGameEntity>("${YourGameEntity::isWanted.name} = $0", true)
            .find()

    override suspend fun getPlayed(): List<YourGameEntity> =
        realm.query<YourGameEntity>("${YourGameEntity::isPlayed.name} = $0", true)
            .find()

    override suspend fun getFavorites(): List<YourGameEntity> =
        realm.query<YourGameEntity>("${YourGameEntity::isFavorite.name} = $0", true)
            .find()
}