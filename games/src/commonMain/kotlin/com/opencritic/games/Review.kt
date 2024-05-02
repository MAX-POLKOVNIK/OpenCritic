package com.opencritic.games

import kotlinx.datetime.Instant

data class Review(
    val id: String,
    val outlet: Outlet,
    val scoreFormat: ReviewScoreFormat,
    val externalUrl: String,
    val platforms: List<Platform>,
    val authors: List<Author>,
    val publishedDate: Instant,
    val title: String?,
    val score: Int?,
    val snippet: String,
)