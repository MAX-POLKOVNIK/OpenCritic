package com.opencritic.halloffame.domain

import com.opencritic.games.GameRank
import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf

data class HallOfFameGame(
    val id: Long,
    val name: String,
    val rank: GameRank?,
    val posterImageUrl: String,
) : ImagePreloadable {
    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(posterImageUrl)
}