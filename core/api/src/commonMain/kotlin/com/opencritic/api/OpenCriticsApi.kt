package com.opencritic.api

import com.opencritic.api.dto.article.ArticleDto
import com.opencritic.api.dto.author.AuthorDto
import com.opencritic.api.dto.calendar.CalendarDto
import com.opencritic.api.dto.deal.DealItemDto
import com.opencritic.api.dto.details.GameDetailsDto
import com.opencritic.api.dto.featured.FeaturedGameListDto
import com.opencritic.api.dto.game.GameSortKey
import com.opencritic.api.dto.game.GameTimeKey
import com.opencritic.api.dto.game.BrowseGameDto
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

interface OpenCriticsApi {
    suspend fun getGamePopular(): List<PopularItemDto>

    suspend fun getDeals(): List<DealItemDto>

    suspend fun getTodayReviewed(): List<ReviewedTodayGameDto>

    suspend fun getUpcoming(): List<ReleasedGameDto>

    suspend fun getRecentlyReleased(): List<ReleasedGameDto>

    suspend fun getHallOfFame(year: Int): List<ReleasedGameDto>

    suspend fun getSwitchFeatured(): FeaturedGameListDto

    suspend fun getXboxFeatured(): FeaturedGameListDto

    suspend fun getPlaystationFeatured(): FeaturedGameListDto

    suspend fun getGame(gameId: Long): GameDetailsDto

    suspend fun getGameMedia(gameId: Long): GameDetailsDto

    suspend fun getGameReviewsLanding(gameId: Long): List<ReviewDto>

    suspend fun getGameReviews(gameId: Long, skip: Int, sort: ReviewSortKey): List<ReviewDto>

    suspend fun search(criteria: String): List<SearchItemDto>

    suspend fun getOutlet(outletId: Int): OutletDto

    suspend fun getOutletReviews(outletId: Int, skip: Int, sort: ReviewSortKey): List<ReviewDto>

    suspend fun getAuthor(authorId: Int): AuthorDto

    suspend fun getAuthorReviews(authorId: Int, skip: Int, sort: ReviewSortKey): List<ReviewDto>

    suspend fun getPlatforms(): List<PlatformDto>

    suspend fun getGames(
        platformShortName: String,
        time: GameTimeKey,
        sort: GameSortKey,
        skip: Int,
        isExclusive: Boolean,
    ): List<BrowseGameDto>

    suspend fun getReviewedThisWeek(): List<BrowseGameDto>

    suspend fun getProfile(token: String): ProfileDto

    suspend fun getLists(token: String): List<GameListDto>

    suspend fun getList(listId: String, token: String): GameListDto

    suspend fun postListAction(
        list: VitalListTypeDto,
        action: VitalListGameActionDto,
        token: String,
    ): GameListDto

    suspend fun getArticlePreviews(skip: Int): List<ArticleDto>

    suspend fun getArticle(articleId: Long): ArticleDto

    suspend fun getCalendar(): CalendarDto
}