package com.opencritic.games

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf
import com.opencritic.remote.images.preloadableImageUrls
import kotlinx.datetime.Instant

data class Review(
    val id: String,
    val gameId: Long,
    val gameName: String,
    val outlet: Outlet,
    val scoreFormat: ReviewScoreFormat,
    val externalUrl: String,
    val platforms: List<Platform>,
    val authors: List<Author>,
    val alias: String?,
    val publishedDate: Instant,
    val title: String?,
    val score: Float?,
    val snippet: String,
    val youtubePlaceholderUrl: String?,
) : ImagePreloadable {

    override val preloadableImageUrls: List<ImageUrl>
        get() =
            preloadableImageListOf(
                outlet.preloadableImageUrls,
                authors.preloadableImageUrls,
                preloadableImageListOf(youtubePlaceholderUrl),
            )
}
