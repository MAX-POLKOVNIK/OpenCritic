package com.opencritic.remote.images

interface ImagePreloader {
    fun load(urls: List<ImageUrl>)
    fun cancel()
}

fun ImagePreloader.load(preloadable: ImagePreloadable) {
    load(preloadable.preloadableImageUrls)
}

fun ImagePreloader.load(list: List<ImagePreloadable>) {
    load(preloadableImageListOf(list))
}