package com.opencritic.database

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

internal class YourGameDaoImpl(
    private val realm: Realm
) : YourGameDao {
    override suspend fun insert(game: YourGameEntity) {
        realm.write { copyToRealm(game) }
    }

    override suspend fun get(id: Long): YourGameEntity? =
        realm.query<YourGameEntity>("${YourGameEntity::id.name} = $0", id)
            .first()
            .find()

    override suspend fun removeNotInterested() {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<YourGameEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getWanted(): List<YourGameEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlayed(): List<YourGameEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): List<YourGameEntity> {
        TODO("Not yet implemented")
    }
}