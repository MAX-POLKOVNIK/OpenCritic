package com.opencritic.game.your.domain

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageUrls

data class GameList(
    val id: String,
    val name: String,
    val gamesCount: Int,
    val shareLink: String,
    val games: List<GameInList>,
) : ImagePreloadable {
    val posters: List<String> =
        games.map { it.posterUrl }

    override val preloadableImageUrls: List<ImageUrl>
        get() = games.preloadableImageUrls
}