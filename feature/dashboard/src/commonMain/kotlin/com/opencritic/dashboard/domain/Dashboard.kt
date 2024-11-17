package com.opencritic.dashboard.domain

import com.opencritic.remote.images.ImagePreloadable
import com.opencritic.remote.images.ImageUrl
import com.opencritic.remote.images.preloadableImageListOf
import com.opencritic.remote.images.preloadableImageUrls

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
) : ImagePreloadable {
    override val preloadableImageUrls: List<ImageUrl>
        get() = preloadableImageListOf(
            popularGames.preloadableImageUrls,
            deals.preloadableImageUrls,
            hallOfFame.preloadableImageUrls,
            switchFeatured.preloadableImageUrls,
            xboxFeatured.preloadableImageUrls,
            playstationFeatured.preloadableImageUrls,
        )
}
