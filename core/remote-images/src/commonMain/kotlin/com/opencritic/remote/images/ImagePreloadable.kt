package com.opencritic.remote.images

import kotlin.jvm.JvmName

interface ImagePreloadable {
    val preloadableImageUrls: List<ImageUrl>
}

private class CompositePreloadable(
    private val list: List<ImagePreloadable>
) : ImagePreloadable {
    override val preloadableImageUrls: List<ImageUrl>
        get() = list.map { it.preloadableImageUrls }
            .flatten()
            .distinct()
}

fun preloadableImageListOf(vararg string: String?): List<ImageUrl> =
    string.mapNotNull { it?.toImageUrl() }
        .distinct()

fun preloadableImageListOf(list: List<String?>): List<ImageUrl> =
    list.mapNotNull { it?.toImageUrl() }
        .distinct()

fun preloadableImageListOf(vararg imageUrl: List<ImageUrl>): List<ImageUrl> =
    imageUrl.toList().flatten().distinct()

fun preloadableImageListOf(list: List<ImagePreloadable>): ImagePreloadable =
    CompositePreloadable(list)

@get:JvmName("preloadableImageUrlsForListOfImagePreloadable")
val List<ImagePreloadable>.preloadableImageUrls: List<ImageUrl>
    get() = map { it.preloadableImageUrls }.flatten().distinct()

@get:JvmName("preloadableImageUrlsForListOfStrings")
val List<String>.preloadableImageUrls: List<ImageUrl>
    get() = toImageUrl()