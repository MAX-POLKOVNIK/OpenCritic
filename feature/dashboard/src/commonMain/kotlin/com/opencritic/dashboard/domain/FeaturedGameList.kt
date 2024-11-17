package com.opencritic.dashboard.domain

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageUrls

data class FeaturedGameList(
    val name: String,
    val description: String,
    val games: List<PosterGame>,
) : ImagePreloadable {
    override val preloadableImageUrls: List<ImageUrl>
        get() = games.preloadableImageUrls
}