package com.opencritic.game.your.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.api.dto.list.GameListDto
import com.opencritic.api.dto.list.ListGameActionDto
import com.opencritic.api.dto.list.VitalListGameActionDto
import com.opencritic.api.dto.list.VitalListTypeDto
import com.opencritic.api.dto.popular.PopularItemDto
import com.opencritic.auth.domain.GetAuthStateInteractor
import com.opencritic.auth.domain.NoTokenException
import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GameList
import com.opencritic.game.your.domain.GameListAction
import com.opencritic.game.your.domain.GameListId
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameRepository
import com.opencritic.games.GameRank
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class YourGameRepositoryImpl(
    private val openCriticApi: OpenCriticsApi,
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : YourGameRepository {
    override suspend fun getLists(): List<GameList> =
        withContext(defaultDispatcher) {
            openCriticApi.getLists(token())
                .map { GameList(it) }
        }

    override suspend fun getList(listId: String): GameList =
        withContext(defaultDispatcher) {
            openCriticApi.getList(listId, token())
                .let { GameList(it) }
        }

    override suspend fun updateGameList(
        gameListId: GameListId,
        action: GameListAction,
        gameId: Long,
    ) {
        withContext(defaultDispatcher) {
            val listDto = when (gameListId) {
                is GameListId.Custon -> throw Exception("Not supported yet")
                GameListId.Favorite -> VitalListTypeDto.Favorite
                GameListId.Played -> VitalListTypeDto.Played
                GameListId.Want -> VitalListTypeDto.Want
            }

            val actionDto = VitalListGameActionDto(
                action = when (action) {
                    GameListAction.Add -> ListGameActionDto.Add
                    GameListAction.Remove -> ListGameActionDto.Remove
                },
                gameId = gameId,
            )

            openCriticApi.postListAction(
                list = listDto,
                action = actionDto,
                token = token()
            )
        }
    }

    override suspend fun getVitalLists(): Map<GameListId, List<Long>> =
        withContext(defaultDispatcher) {
            val token = getAuthStateInteractor().getOrNull()?.authToken

            if (token.isNullOrBlank()) {
                mapOf(
                    GameListId.Want to emptyList(),
                    GameListId.Played to emptyList(),
                    GameListId.Favorite to emptyList(),
                )
            } else {
                val profile = openCriticApi.getProfile(token)

                mapOf(
                    GameListId.Want to profile.vitalLists.wantList.gamesEnhanced.map { it.id },
                    GameListId.Played to profile.vitalLists.playedList.gamesEnhanced.map { it.id },
                    GameListId.Favorite to profile.vitalLists.favoriteList.gamesEnhanced.map { it.id },
                )
            }
        }

    private suspend fun token(): String =
        getAuthStateInteractor().getOrThrow().authToken ?: throw NoTokenException()

    private fun GameList(dto: GameListDto): GameList =
        GameList(
            id = dto.id,
            name = dto.label,
            gamesCount = dto.numGames,
            shareLink = "https://opencritic.com/list/${dto.id}",
            games = dto.gamesPopulated.map { GameInList(it) }
        )

    private fun GameInList(dto: PopularItemDto): GameInList =
        GameInList(
            id = dto.id,
            name = dto.name,
            posterUrl = dto.images.box?.og?.prefixedImageUrl() ?: dto.images.banner?.og?.prefixedImageUrl() ?: "",
            rank = GameRank(dto.tier, dto.topCriticScore),
        )
}