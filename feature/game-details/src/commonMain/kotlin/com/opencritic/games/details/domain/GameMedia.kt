package com.opencritic.games.details.domain

import com.opencritic.games.Trailer

data class GameMedia(
    val gameName: String,
    val trailers: List<Trailer>,
    val screenshotUrls: List<String>,
)