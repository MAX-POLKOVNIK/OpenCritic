package com.opencritic.remote.images

interface ImagePreloader {
    fun load(urls: List<ImageUrl>)
    fun cancel()
}