package com.opencritic.navigation

data class GameDetailsRoute(val gameId: Long) : Route("game/${gameId}")
data class UrlRoute(val url: String) : Route(url)