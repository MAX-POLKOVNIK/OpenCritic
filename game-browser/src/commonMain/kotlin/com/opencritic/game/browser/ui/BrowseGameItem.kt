package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.ui.RankCircleIndicatorItem
import com.opencritic.games.details.ui.createCriticsRecommendIndicator
import com.opencritic.games.details.ui.createTopCriticAverageIndicator
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.images.Icons
import com.opencritic.resources.images.SharedImageResource
import com.opencritic.resources.images.SharedImages
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class BrowseGameItem(
    val id: Long,
    val imageUrl: String,
    val isTierVisible: Boolean,
    val tierImageResource: SharedImageResource,
    val isTopCriticVisibleIndicator: Boolean,
    val topCriticScoreIndicator: RankCircleIndicatorItem,
    val isPercentRecommendedIndicatorVisible: Boolean,
    val percentRecommendedIndicator: RankCircleIndicatorItem,
    val nameText: String,
    val dateImageResource: IconResource,
    val dateText: TextSource,
    val onClick: () -> Unit,
)

fun BrowseGameItem(
    game: BrowseGame,
    isPercentRecommendedVisible: Boolean,
    onClick: () -> Unit,
): BrowseGameItem =
    BrowseGameItem(
        id = game.id,
        imageUrl = game.imageUrl,
        nameText = game.name,
        dateImageResource = Icons.tabCalendar,
        dateText = game.releaseDate.toLocalDateTime(TimeZone.UTC).date format DateTextSource.Format.Long,
        isTierVisible = game.rank != null,
        tierImageResource = when (game.rank?.tier) {
            Tier.Mighty -> SharedImages.mightyMan
            Tier.Strong -> SharedImages.strongMan
            Tier.Fair -> SharedImages.fairMan
            Tier.Weak -> SharedImages.weakMan
            null -> SharedImages.weakMan
        },
        isTopCriticVisibleIndicator = game.rank != null,
        topCriticScoreIndicator = createTopCriticAverageIndicator(
            gameRank = game.rank ?: GameRank(Tier.Weak, 0)
        ),
        isPercentRecommendedIndicatorVisible = isPercentRecommendedVisible && game.rank != null,
        percentRecommendedIndicator = createCriticsRecommendIndicator(game.rank?.tier ?: Tier.Weak, game.percentRecommended),
        onClick = onClick,
    )

@Suppress("FunctionName")
fun BrowseGameItem_PreviewData(): BrowseGameItem =
    BrowseGameItem(
        id = 1,
        imageUrl = "https://img.opencritic.com/game/4504/BZrxOMDi.jpg",
        nameText = "Super Mario Odyssey",
        dateImageResource = Icons.tabCalendar,
        dateText = "October 27, 2017".asTextSource(),
        isTierVisible = true,
        tierImageResource = SharedImages.mightyMan,
        isTopCriticVisibleIndicator = true,
        topCriticScoreIndicator = createTopCriticAverageIndicator(
            gameRank = GameRank(Tier.Mighty, 97)
        ),
        isPercentRecommendedIndicatorVisible = true,
        percentRecommendedIndicator = createCriticsRecommendIndicator(Tier.Mighty, 90f),
        onClick = {},
    )