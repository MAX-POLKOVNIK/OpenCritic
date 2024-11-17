package com.opencritic.games

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf

data class Author(
    val id: Int,
    val name: String,
    val imageUrl: String? = null,
) : ImagePreloadable {

    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(imageUrl)
}