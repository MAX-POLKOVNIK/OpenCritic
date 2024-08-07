package com.opencritic.halloffame.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.api.dto.image.prefixedImageUrl
import com.opencritic.games.GameRank
import com.opencritic.halloffame.domain.HallOfFameGame
import com.opencritic.halloffame.domain.HallOfFameGameList
import com.opencritic.halloffame.domain.HallOfFameRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class HallOfFameRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : HallOfFameRepository {
    override suspend fun getHallOfFame(year: Int): HallOfFameGameList =
        withContext(defaultDispatcher) {
            HallOfFameGameList(
                year = year,
                games = openCriticsApi.getHallOfFame(year)
                    .map {
                        HallOfFameGame(
                            id = it.id,
                            name = it.name,
                            posterImageUrl = it.images.box?.sm?.prefixedImageUrl() ?: it.images.banner?.sm?.prefixedImageUrl() ?: "",
                            rank = GameRank(it.tier, it.topCriticScore),
                        )
                    }
            )
    }
}