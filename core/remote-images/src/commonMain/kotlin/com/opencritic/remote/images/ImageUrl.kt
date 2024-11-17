package com.opencritic.remote.images

data class ImageUrl(
    val path: String,
)

fun String.toImageUrl(): ImageUrl =
    ImageUrl(this)

fun List<String>.toImageUrl(): List<ImageUrl> =
    map { it.toImageUrl() }
        .distinct()