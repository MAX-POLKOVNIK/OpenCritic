package com.opencritic.api

import com.opencritic.api.dto.article.ArticleDto
import com.opencritic.api.dto.author.AuthorDto
import com.opencritic.api.dto.calendar.CalendarDto
import com.opencritic.api.dto.deal.DealItemDto
import com.opencritic.api.dto.details.GameDetailsDto
import com.opencritic.api.dto.featured.FeaturedGameListDto
import com.opencritic.api.dto.game.BrowseGameDto
import com.opencritic.api.dto.game.GameSortKey
import com.opencritic.api.dto.game.GameTimeKey
import com.opencritic.api.dto.list.GameListDto
import com.opencritic.api.dto.list.VitalListGameActionDto
import com.opencritic.api.dto.list.VitalListTypeDto
import com.opencritic.api.dto.outlet.OutletDto
import com.opencritic.api.dto.platform.PlatformDto
import com.opencritic.api.dto.popular.PopularItemDto
import com.opencritic.api.dto.profile.ProfileDto
import com.opencritic.api.dto.review.ReviewedTodayGameDto
import com.opencritic.api.dto.released.ReleasedGameDto
import com.opencritic.api.dto.review.ReviewDto
import com.opencritic.api.dto.review.ReviewSortKey
import com.opencritic.api.dto.search.SearchItemDto
import com.opencritic.api.exceptions.NoInternetException
import com.opencritic.api.exceptions.UnknownException
import com.opencritic.api.exceptions.UnsuccessfulResponseException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

internal class OpenCriticsApiImpl(
    private val client: HttpClient,
) : OpenCriticsApi {
    override suspend fun getGamePopular(): List<PopularItemDto> =
        get("game/popular")

    override suspend fun getDeals(): List<DealItemDto> =
        get("game/deals")

    override suspend fun getTodayReviewed(): List<ReviewedTodayGameDto> =
       get("game/reviewed-today")

    override suspend fun getUpcoming(): List<ReleasedGameDto> =
       get("game/upcoming")

    override suspend fun getRecentlyReleased(): List<ReleasedGameDto> =
        get("game/recently-released")

    override suspend fun getHallOfFame(year: Int): List<ReleasedGameDto> =
        get("game/hall-of-fame/$year")

    override suspend fun getSwitchFeatured(): FeaturedGameListDto =
        get("editor-sequence/switch-featured")

    override suspend fun getXboxFeatured(): FeaturedGameListDto =
        get("editor-sequence/xbox-featured")

    override suspend fun getPlaystationFeatured(): FeaturedGameListDto =
        get("editor-sequence/playstation-featured")

    override suspend fun getGame(gameId: Long): GameDetailsDto =
        get("game/${gameId}")

    override suspend fun getGameMedia(gameId: Long): GameDetailsDto =
        get("game/${gameId}/?fullmedia=true")

    override suspend fun getGameReviewsLanding(gameId: Long): List<ReviewDto> =
        get("reviews/game/$gameId/landing")

    override suspend fun getGameReviews(gameId: Long, skip: Int, sort: ReviewSortKey): List<ReviewDto> =
        get("reviews/game/$gameId/?skip=$skip&sort=${sort.queryValue}")

    override suspend fun search(criteria: String): List<SearchItemDto> =
        get("meta/search?criteria=$criteria")

    override suspend fun getOutlet(outletId: Int): OutletDto =
        get("outlet/$outletId")

    override suspend fun getOutletReviews(
        outletId: Int,
        skip: Int,
        sort: ReviewSortKey
    ): List<ReviewDto> =
        get("reviews/outlet/$outletId/?skip=$skip&sort=${sort.queryValue}")

    override suspend fun getAuthor(authorId: Int): AuthorDto =
        get("author/$authorId")

    override suspend fun getAuthorReviews(
        authorId: Int,
        skip: Int,
        sort: ReviewSortKey
    ): List<ReviewDto> =
        get("reviews/author/$authorId/?skip=$skip&sort=${sort.queryValue}")

    override suspend fun getPlatforms(): List<PlatformDto> =
        get("platform")

    override suspend fun getGames(
        platformShortName: String,
        time: GameTimeKey,
        sort: GameSortKey,
        skip: Int,
        isExclusive: Boolean,
    ): List<BrowseGameDto> =
        get("game?platforms=$platformShortName&skip=$skip&sort=${sort.queryValue}&time=${time.queryKey}&exclusive=$isExclusive")

    override suspend fun getReviewedThisWeek(): List<BrowseGameDto> =
        get("game/reviewed-this-week")

    override suspend fun getProfile(token: String): ProfileDto =
        get("profile", token)

    override suspend fun getLists(token: String): List<GameListDto> =
        get("game-list", token)

    override suspend fun getList(listId: String, token: String): GameListDto =
        get("game-list/$listId", token)

    override suspend fun postListAction(
        list: VitalListTypeDto,
        action: VitalListGameActionDto,
        token: String,
    ): GameListDto =
        post(
            url = "game-list/common/${list.value}",
            body = action,
            token = token
        )

    override suspend fun getArticlePreviews(skip: Int): List<ArticleDto> =
        get("article/list?skip=$skip")

    override suspend fun getArticle(articleId: Long): ArticleDto =
        get("article/$articleId")

    override suspend fun getCalendar(): CalendarDto =
        get("calendar/v2")

    private val baseUrl = "https://api.opencritic.com/api/"

    private suspend inline fun <reified ResponseBody, reified RequestBody> post(
        url: String,
        body: RequestBody,
        token: String? = null,
    ): ResponseBody =
        try {
            val response = client.post(baseUrl + url, headersAndBody(token, body))

            if (response.status.value >= 400) {
                throw UnsuccessfulResponseException(response.status.value)
            } else {
                response.body()
            }
        } catch (ex: Throwable) {
            throw when (ex) {
                is IOException -> NoInternetException(ex)
                is UnsuccessfulResponseException -> ex
                else -> UnknownException(ex)
            }
        }

    private suspend inline fun <reified ResponseBody> get(
        url: String,
        token: String? = null,
    ): ResponseBody =
        try {
            val response = client.get(baseUrl + url, headers(token))

            if (response.status.value >= 400) {
                throw UnsuccessfulResponseException(response.status.value)
            } else {
                response.body()
            }
        } catch (ex: Throwable) {
            throw when (ex) {
                is IOException -> NoInternetException(ex)
                is UnsuccessfulResponseException -> ex
                else -> UnknownException(ex)
            }
        }

    private inline fun <reified T> headersAndBody(
        token: String? = null,
        body: T
    ): HttpRequestBuilder.() -> Unit = {
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
            token?.let { append("x-access-token", it) }
        }

        contentType(ContentType.Application.Json)
        setBody(body)
    }

    private fun headers(token: String? = null): HttpRequestBuilder.() -> Unit = {
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
            token?.let { append("x-access-token", it) }
        }
    }
}