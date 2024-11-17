package com.opencritic.games.details.domain

import com.opencritic.games.Trailer
import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf
import com.opencritic.remote.images.preloadableImageUrls

data class GameMedia(
    val gameName: String,
    val trailers: List<Trailer>,
    val screenshotUrls: List<String>,
) : ImagePreloadable {

    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(trailers.preloadableImageUrls, screenshotUrls.preloadableImageUrls)
}