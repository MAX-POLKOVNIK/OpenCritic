package com.opencritic.dashboard.domain

import com.opencritic.remote.images.ImagePreloadable

data class GameDeal(
    val game: PosterGame,
    val name: String,
    val price: Float?,
    val externalUrl: String,
) : ImagePreloadable by game