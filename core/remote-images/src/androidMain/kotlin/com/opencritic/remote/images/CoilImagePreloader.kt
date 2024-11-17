package com.opencritic.remote.images

import android.content.Context
import coil.imageLoader
import coil.request.Disposable
import coil.request.ImageRequest

class CoilImagePreloader(
    private val context: Context,
) : ImagePreloader {

    private val requestsMap: MutableMap<ImageUrl, Disposable> = mutableMapOf()

    override fun load(urls: List<ImageUrl>) {
        urls.forEach { load(it) }
    }

    override fun cancel() {
        requestsMap.values.forEach { it.dispose() }

        requestsMap.clear()
    }

    private fun load(url: ImageUrl) {
        val request = ImageRequest.Builder(context)
            .data(url.path)
            .build()

        val disposable = context.imageLoader.enqueue(request)

        requestsMap[url] = disposable
    }
}