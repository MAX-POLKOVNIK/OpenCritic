package com.opencritic.game.browser.ui

import com.opencritic.game.browser.domain.BrowseGame
import com.opencritic.games.GameRank
import com.opencritic.games.Tier
import com.opencritic.games.details.ui.RankCircleIndicatorItem
import com.opencritic.games.details.ui.createCriticsRecommendIndicator
import com.opencritic.games.details.ui.createTopCriticAverageIndicator
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResource
import com.opencritic.resources.ImageResourceProvider
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class BrowseGameItem(
    val id: Long,
    val imageUrl: String,
    val isTierVisible: Boolean,
    val tierImageResource: ImageResource,
    val isTopCriticVisibleIndicator: Boolean,
    val topCriticScoreIndicator: RankCircleIndicatorItem,
    val isPercentRecommendedIndicatorVisible: Boolean,
    val percentRecommendedIndicator: RankCircleIndicatorItem,
    val nameText: String,
    val dateImageResource: ImageResource,
    val dateText: String,
    val onClick: () -> Unit,
)

fun BrowseGameItem(
    game: BrowseGame,
    isPercentRecommendedVisible: Boolean,
    imageResourceProvider: ImageResourceProvider,
    dateFormatter: DateFormatter,
    onClick: () -> Unit,
): BrowseGameItem =
    BrowseGameItem(
        id = game.id,
        imageUrl = game.imageUrl,
        nameText = game.name,
        dateImageResource = imageResourceProvider.tabCalendar,
        dateText = dateFormatter.formatFull(game.releaseDate.toLocalDateTime(TimeZone.UTC).date),
        isTierVisible = game.rank != null,
        tierImageResource = when (game.rank?.tier) {
            Tier.Mighty -> imageResourceProvider.mightyMan
            Tier.Strong -> imageResourceProvider.strongMan
            Tier.Fair -> imageResourceProvider.fairMan
            Tier.Weak -> imageResourceProvider.weakMan
            null -> imageResourceProvider.weakMan
        },
        isTopCriticVisibleIndicator = game.rank != null,
        topCriticScoreIndicator = createTopCriticAverageIndicator(
            gameRank = game.rank ?: GameRank(Tier.Weak, 0)
        ),
        isPercentRecommendedIndicatorVisible = isPercentRecommendedVisible && game.rank != null,
        percentRecommendedIndicator = createCriticsRecommendIndicator(game.rank?.tier ?: Tier.Weak, game.percentRecommended.toInt()),
        onClick = onClick,
    )

@Suppress("FunctionName")
fun BrowseGameItem_PreviewData(
    imageResourceProvider: ImageResourceProvider
): BrowseGameItem =
    BrowseGameItem(
        id = 1,
        imageUrl = "https://img.opencritic.com/game/4504/BZrxOMDi.jpg",
        nameText = "Super Mario Odyssey",
        dateImageResource = imageResourceProvider.tabCalendar,
        dateText = "October 27, 2017",
        isTierVisible = true,
        tierImageResource = imageResourceProvider.mightyMan,
        isTopCriticVisibleIndicator = true,
        topCriticScoreIndicator = createTopCriticAverageIndicator(
            gameRank = GameRank(Tier.Mighty, 97)
        ),
        isPercentRecommendedIndicatorVisible = true,
        percentRecommendedIndicator = createCriticsRecommendIndicator(Tier.Mighty, 90),
        onClick = {},
    )