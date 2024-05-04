package com.opencritic.api

import com.opencritic.api.dto.author.AuthorDto
import com.opencritic.api.dto.deal.DealItemDto
import com.opencritic.api.dto.details.GameDetailsDto
import com.opencritic.api.dto.featured.FeaturedGameListDto
import com.opencritic.api.dto.game.BrowseGameDto
import com.opencritic.api.dto.game.GameSortKey
import com.opencritic.api.dto.game.GameTimeKey
import com.opencritic.api.dto.outlet.OutletDto
import com.opencritic.api.dto.platform.PlatformDto
import com.opencritic.api.dto.popular.PopularItemDto
import com.opencritic.api.dto.review.ReviewedTodayGameDto
import com.opencritic.api.dto.released.ReleasedGameDto
import com.opencritic.api.dto.review.ReviewDto
import com.opencritic.api.dto.review.ReviewSortKey
import com.opencritic.api.dto.search.SearchItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders


private const val baseUrl = "https://api.opencritic.com/api/"

internal class OpenCriticsApiImpl(
    private val client: HttpClient,
) : OpenCriticsApi {
    override suspend fun getGamePopular(): List<PopularItemDto> =
        client.get(baseUrl + "game/popular").body()

    override suspend fun getDeals(): List<DealItemDto> =
        client.get(baseUrl + "game/deals").body()

    override suspend fun getTodayReviewed(): List<ReviewedTodayGameDto> =
        client.get(baseUrl + "game/reviewed-today").body()

    override suspend fun getUpcoming(): List<ReleasedGameDto> =
        client.get(baseUrl + "game/upcoming").body()

    override suspend fun getRecentlyReleased(): List<ReleasedGameDto> =
        client.get(baseUrl + "game/recently-released").body()

    override suspend fun getHallOfFame(year: Int): List<ReleasedGameDto> =
        client.get(baseUrl + "game/hall-of-fame/$year").body()

    override suspend fun getSwitchFeatured(): FeaturedGameListDto =
        client.get(baseUrl + "editor-sequence/switch-featured").body()

    override suspend fun getXboxFeatured(): FeaturedGameListDto =
        client.get(baseUrl + "editor-sequence/xbox-featured").body()

    override suspend fun getPlaystationFeatured(): FeaturedGameListDto =
        client.get(baseUrl + "editor-sequence/playstation-featured").body()

    override suspend fun getGame(gameId: Long): GameDetailsDto =
        client.get(baseUrl + "game/${gameId}").body()

    override suspend fun getGameReviews(gameId: Long, skip: Int, sort: ReviewSortKey): List<ReviewDto> =
        client.get(baseUrl + "reviews/game/$gameId/?skip=$skip&sort=${sort.queryValue}").body()

    override suspend fun search(criteria: String): List<SearchItemDto> =
        client.get(baseUrl + "meta/search?criteria=$criteria") {
            headers {
                append(HttpHeaders.CacheControl, "no-cache")
                append(HttpHeaders.Host, "api.opencritic.com")
                append(HttpHeaders.Origin, "https://opencritic.com")
                append(HttpHeaders.Referrer, "https://opencritic.com/")
                append(HttpHeaders.Accept, "application/json")
                append(HttpHeaders.UserAgent, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:124.0) Gecko/20100101 Firefox/124.0")
                append("Sec-Fetch-Site", "same-site")
                append("Sec-Fetch-Dest", "empty")
                append("Sec-Fetch-Mode", "cors")
            }
        }.body()

    override suspend fun getOutlet(outletId: Int): OutletDto =
        client.get(baseUrl + "outlet/$outletId").body()

    override suspend fun getOutletReviews(
        outletId: Int,
        skip: Int,
        sort: ReviewSortKey
    ): List<ReviewDto> =
        client.get(baseUrl + "reviews/outlet/$outletId/?skip=$skip&sort=${sort.queryValue}").body()

    override suspend fun getAuthor(authorId: Int): AuthorDto =
        client.get(baseUrl + "author/$authorId").body()

    override suspend fun getAuthorReviews(
        authorId: Int,
        skip: Int,
        sort: ReviewSortKey
    ): List<ReviewDto> =
        client.get(baseUrl + "reviews/author/$authorId/?skip=$skip&sort=${sort.queryValue}").body()

    override suspend fun getPlatforms(): List<PlatformDto> =
        client.get(baseUrl + "platform").body()

    override suspend fun getGames(
        platformShortName: String,
        time: GameTimeKey,
        sort: GameSortKey,
        skip: Int,
    ): List<BrowseGameDto> =
        client.get(baseUrl + "game?platforms=$platformShortName&skip=$skip&sort=${sort.queryValue}&time=${time.queryKey}").body()
}