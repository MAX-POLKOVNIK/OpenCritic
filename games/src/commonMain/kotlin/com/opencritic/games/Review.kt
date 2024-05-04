package com.opencritic.games

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
)