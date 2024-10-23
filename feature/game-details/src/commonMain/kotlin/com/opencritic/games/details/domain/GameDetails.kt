package com.opencritic.games.details.domain

import com.opencritic.game.your.domain.YourGame
import com.opencritic.games.Company
import com.opencritic.games.GameRank
import com.opencritic.games.Platform
import com.opencritic.games.Review
import com.opencritic.games.Trailer
import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf
import com.opencritic.remote.images.preloadableImageUrls
import kotlinx.datetime.Instant

data class GameDetails(
    val id: Long,
    val url: String,
    val squareUrl: String,
    val bannerUrl: String,
    val posterUrl: String,
    val yourGame: YourGame,
    val name: String,
    val companies: List<Company>,
    val releaseDate: Instant,
    val platforms: List<Platform>,
    val rank: GameRank?,
    val gameRating: GameRating,
    val recommendPercent: Float?,
    val reviews: List<Review>,
    val reviewCount: Int,
    val trailers: List<Trailer>,
    val screenshotUrls: List<String>,
) : ImagePreloadable {

    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(
            preloadableImageListOf(squareUrl, bannerUrl, posterUrl),
            reviews.preloadableImageUrls,
            trailers.preloadableImageUrls,
            preloadableImageListOf(screenshotUrls),
        )
}
