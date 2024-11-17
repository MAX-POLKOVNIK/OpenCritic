package com.opencritic.halloffame.domain

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageUrls

data class HallOfFameGameList(
    val year: Int,
    val games: List<HallOfFameGame>
) : ImagePreloadable {
    override val preloadableImageUrls: List<ImageUrl>
        get() = games.preloadableImageUrls
}