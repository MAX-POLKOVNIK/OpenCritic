package com.opencritic.games.details.ui

import com.opencritic.games.Trailer

data class TrailerItem(
    val titleText: String,
    val thumbnailUrl: String,
    private val onClick: (TrailerItem) -> Unit,
) : MediaItem() {
    constructor(trailer: Trailer, onClick: (TrailerItem) -> Unit) : this(
        titleText = trailer.title,
        thumbnailUrl = trailer.thumbnailUrl,
        onClick = onClick,
    )

    fun click() = onClick(this)
}