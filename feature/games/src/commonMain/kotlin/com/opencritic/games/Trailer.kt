package com.opencritic.games

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.toImageUrl

data class Trailer(
    val title: String,
    val thumbnailUrl: String,
    val externalUrl: String,
) : ImagePreloadable {
    override val preloadableImageUrls: List<ImageUrl> =
        listOf(thumbnailUrl.toImageUrl())
}