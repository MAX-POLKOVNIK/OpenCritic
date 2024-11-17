package com.opencritic.game.browser.domain

import com.opencritic.games.GameRank
import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf
import kotlinx.datetime.Instant

data class BrowseGame(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val rank: GameRank?,
    val percentRecommended: Float,
    val releaseDate: Instant,
) : ImagePreloadable {

    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(imageUrl)
}