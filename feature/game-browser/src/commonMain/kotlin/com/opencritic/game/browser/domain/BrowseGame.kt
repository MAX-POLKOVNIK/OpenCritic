package com.opencritic.game.browser.domain

import com.opencritic.games.GameRank
import com.opencritic.remote.images.ImageUrl
import kotlinx.datetime.Instant

data class BrowseGame(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val rank: GameRank?,
    val percentRecommended: Float,
    val releaseDate: Instant,
)

val BrowseGame.image: ImageUrl
    get() = ImageUrl(imageUrl)

val List<BrowseGame>.images: List<ImageUrl>
    get() = map { it.image }