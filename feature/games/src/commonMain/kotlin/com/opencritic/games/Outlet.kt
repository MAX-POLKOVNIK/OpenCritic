package com.opencritic.games

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf

data class Outlet(
    val id: Int,
    val name: String,
    val isContributor: Boolean,
    val imageUrl: String,
) : ImagePreloadable {

    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(imageUrl)
}