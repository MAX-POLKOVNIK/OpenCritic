package com.opencritic.dashboard.domain

import com.opencritic.remote.images.ImageUrl

data class Dashboard(
    val popularGames: List<PosterGame>,
    val deals: List<GameDeal>,
    val recentlyReleased: List<GameItem>,
    val upcoming: List<GameItem>,
    val reviewedToday: List<GameItem>,
    val hallOfFame: List<PosterGame>,
    val switchFeatured: FeaturedGameList,
    val xboxFeatured: FeaturedGameList,
    val playstationFeatured: FeaturedGameList,
)

val Dashboard.images: List<ImageUrl>
    get() =
        listOf(
            popularGames.map { ImageUrl(it.posterUrl) },
            deals.map { ImageUrl(it.game.posterUrl) },
            hallOfFame.map { ImageUrl(it.posterUrl) },
            switchFeatured.games.map { ImageUrl(it.posterUrl) },
            xboxFeatured.games.map { ImageUrl(it.posterUrl) },
            playstationFeatured.games.map { ImageUrl(it.posterUrl) },
        )
            .flatten()
            .distinct()
