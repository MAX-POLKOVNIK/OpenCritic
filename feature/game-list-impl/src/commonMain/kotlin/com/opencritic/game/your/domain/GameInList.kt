package com.opencritic.game.your.domain

import com.opencritic.games.GameRank
import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf

data class GameInList(
    val id: Long,
    val name: String,
    val posterUrl: String,
    val rank: GameRank?,
) : ImagePreloadable {

    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(posterUrl)
}