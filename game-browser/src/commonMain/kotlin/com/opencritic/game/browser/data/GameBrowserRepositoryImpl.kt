package com.opencritic.game.browser.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.game.GameSortKey
import com.opencritic.api.dto.game.GameTimeKey
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.game.browser.domain.GameBrowserRepository
import com.opencritic.game.browser.domain.GameSorting
import com.opencritic.game.browser.domain.GameTimeframe
import com.opencritic.games.GameRank
import com.opencritic.games.Platform
import com.opencritic.games.Tier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class GameBrowserRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GameBrowserRepository {
    override suspend fun getPlatforms(): List<Platform> =
        withContext(defaultDispatcher) {
            openCriticsApi.getPlatforms()
                .map { dto ->
                    Platform(
                        name = dto.name,
                        code = dto.shortName,
                    )
                }
        }

    override suspend fun getGames(
        platformCode: String,
        time: GameTimeframe,
        sorting: GameSorting,
        skip: Int,
    ): List<BrowseGame> =
        withContext(defaultDispatcher) {
            openCriticsApi
                .getGames(
                    platformShortName = platformCode,
                    time = when (time) {
                        GameTimeframe.AllTIme -> GameTimeKey.AllTime
                        GameTimeframe.Last90Days -> GameTimeKey.Last90
                        GameTimeframe.Upcoming -> GameTimeKey.Upcoming
                        is GameTimeframe.Year -> GameTimeKey.Year(time.year)
                    },
                    sort = when (sorting) {
                        GameSorting.ReleaseDate -> GameSortKey.Date
                        GameSorting.Score -> GameSortKey.Score
                        GameSorting.ReviewsCount -> GameSortKey.NumReviews
                        GameSorting.PercentRecommended -> GameSortKey.PercentRecommended
                        GameSorting.AtoZ -> GameSortKey.Name
                    },
                    skip = skip
                )
                .map { dto ->
                    BrowseGame(
                        id = dto.id,
                        name = dto.name,
                        rank = GameRank(dto.tier, dto.topCriticScore),
                        percentRecommended = dto.percentRecommended,
                        releaseDate = dto.firstReleaseDate,
                        imageUrl = dto.images?.banner?.sm?.prefixedImageUrl() ?: "",
                    )
                }
        }
}