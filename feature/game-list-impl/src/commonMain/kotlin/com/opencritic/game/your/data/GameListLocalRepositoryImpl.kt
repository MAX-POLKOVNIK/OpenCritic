package com.opencritic.game.your.data

import com.opencritic.database.list.GameInListDao
import com.opencritic.database.list.GameInListEntity
import com.opencritic.database.list.GameListDao
import com.opencritic.database.list.GameListEntity
import com.opencritic.database.list.GameTier
import com.opencritic.database.list.ListGameRelationDao
import com.opencritic.database.list.ListGameRelationEntity
import com.opencritic.database.list.VitalType
import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GameList
import com.opencritic.game.your.domain.GameListAction
import com.opencritic.game.your.domain.GameListId
import com.opencritic.game.your.domain.GameListLocalRepository
import com.opencritic.game.your.domain.uuidString
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class GameListLocalRepositoryImpl(
    private val gameInListDao: GameInListDao,
    private val gameListDao: GameListDao,
    private val listGameRelationDao: ListGameRelationDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GameListLocalRepository {
    override suspend fun getLists(): List<GameList> =
        withContext(defaultDispatcher) {
            val listEntities =
                gameListDao.getAll()
                    .takeUnless { it.isEmpty() }
                    ?: createLists()

            val listWithGames =
                listEntities.associateWith {
                    gameInListDao.getAllInList(it.id)
                }

            listWithGames.map { (listEntity, gameEntities) ->
                GameList(listEntity, gameEntities)
            }
        }

    override suspend fun getList(listId: String): GameList =
        withContext(defaultDispatcher) {
            val listEntity = gameListDao.get(listId)

            requireNotNull(listEntity) { "Requested list with ID = $listId doesn't exists" }

            val gameEntities = gameInListDao.getAllInList(listId)

            GameList(listEntity, gameEntities)
        }

    override suspend fun updateGameList(
        gameListId: GameListId,
        action: GameListAction,
        game: GameInList,
    ) = withContext(defaultDispatcher) {
        val type = VitalType(gameListId)
        val list = requireNotNull(gameListDao.getByVitalType(type))

        if (gameListId == GameListId.Want && action == GameListAction.Add) {
            val playedList = requireNotNull(gameListDao.getByVitalType(VitalType.Played))

            listGameRelationDao.delete(ListGameRelationEntity(playedList.id, game.id))
        }

        if (gameListId == GameListId.Played && action == GameListAction.Add) {
            val wantList = requireNotNull(gameListDao.getByVitalType(VitalType.Want))

            listGameRelationDao.delete(ListGameRelationEntity(wantList.id, game.id))
        }

        when (action) {
            GameListAction.Add -> listGameRelationDao.insert(ListGameRelationEntity(list.id, game.id))
            GameListAction.Remove -> listGameRelationDao.delete(ListGameRelationEntity(list.id, game.id))
        }

        gameInListDao.insert(
            GameInListEntity(game)
        )
    }

    override suspend fun getVitalLists(): Map<GameListId, List<Long>> =
        withContext(defaultDispatcher) {
            val listEntities =
                gameListDao.getAll()
                    .takeUnless { it.isEmpty() }
                    ?: createLists()

            listEntities.filter { it.isVital }
                .associate {
                    GameListId(it) to listGameRelationDao.getByListId(it.id).map { rlg -> rlg.gameId }
                }
        }

    private suspend fun createLists(): List<GameListEntity> =
        listOf(
            GameListEntity(
                id = uuidString(),
                name = "Your Want-To-Play Games",
                isVital = true,
                vitalType = VitalType.Want,
            ),
            GameListEntity(
                id = uuidString(),
                name = "Your Played Games",
                isVital = true,
                vitalType = VitalType.Played,
            ),
            GameListEntity(
                id = uuidString(),
                name = "Your Favorite Games",
                isVital = true,
                vitalType = VitalType.Favorite,
            ),
        ).onEach {
            gameListDao.insert(it)
        }

    private fun VitalType(id: GameListId): VitalType =
        when (id) {
            is GameListId.Custom -> throw Exception("Not supported yet")
            GameListId.Favorite -> VitalType.Favorite
            GameListId.Played -> VitalType.Played
            GameListId.Want -> VitalType.Want
        }

    private fun GameListId(listEntity: GameListEntity): GameListId =
        when (listEntity.vitalType) {
            VitalType.Want -> GameListId.Want
            VitalType.Played -> GameListId.Played
            VitalType.Favorite -> GameListId.Favorite
            null -> GameListId.Custom(listEntity.id)
        }

    private fun GameList(
        listEntity: GameListEntity,
        gameEntities: List<GameInListEntity>
    ): GameList =
        GameList(
            id = listEntity.id,
            name = listEntity.name,
            gamesCount = gameEntities.size,
            shareLink = "",
            games = gameEntities.map { gameEntity ->
                GameInList(gameEntity)
            }
        )

    private fun GameInListEntity(game: GameInList): GameInListEntity =
        GameInListEntity(
            id = game.id,
            name = game.name,
            posterUrl = game.posterUrl,
            score = game.rank?.score ?: 0,
            tier = GameTier(game.rank?.tier),
        )

    private fun GameInList(gameEntity: GameInListEntity): GameInList =
        GameInList(
            id = gameEntity.id,
            name = gameEntity.name,
            posterUrl = gameEntity.posterUrl,
            rank = GameRank(gameEntity.tier, gameEntity.score)
        )

    private fun GameTier(tier: Tier?): GameTier? =
        tier?.let {
            when (it) {
                Tier.Mighty -> GameTier.Mighty
                Tier.Strong -> GameTier.Strong
                Tier.Fair -> GameTier.Fair
                Tier.Weak -> GameTier.Weak
            }
        }

    private fun GameRank(tier: GameTier?, score: Int): GameRank? =
        tier?.let {
            GameRank(
                tier = when (tier) {
                    GameTier.Mighty -> Tier.Mighty
                    GameTier.Strong -> Tier.Strong
                    GameTier.Fair -> Tier.Fair
                    GameTier.Weak -> Tier.Weak
                },
                score = score
            )
        }
}