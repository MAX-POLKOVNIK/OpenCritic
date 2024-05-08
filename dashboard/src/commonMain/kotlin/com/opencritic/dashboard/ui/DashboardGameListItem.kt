package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameItem
import com.opencritic.games.GameRank
import com.opencritic.games.GameRankModel
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

data class DashboardGameListItem(
    override val id: Long,
    val rank: GameRankModel?,
    val nameText: String,
    val dateText: String,
    private val onClick: (DashboardGameListItem) -> Unit,
) : ListItem<Long> {
    constructor(
        gameItem: GameItem,
        imageResourceProvider: ImageResourceProvider,
        dateFormatter: DateFormatter,
        onClick: (DashboardGameListItem) -> Unit,
    ) : this(
        id = gameItem.id,
        rank = GameRankModel(imageResourceProvider, gameItem.rank),
        nameText = gameItem.name,
        dateText = dateFormatter.formatShort(gameItem.releaseDate.toLocalDateTime(TimeZone.UTC).date),
        onClick = onClick,
    )

    fun click() = onClick(this)
}

@Suppress("FunctionName")
fun DashboardGameListItem_PreviewData(
    imageResourceProvider: ImageResourceProvider,
): DashboardGameListItem =
    DashboardGameListItem(
        id = 1,
        rank = GameRankModel(headImageResource = imageResourceProvider.fairHead, scoreText = "15"),
        nameText = "Game ldskfhds  sdkjhfjsd",
        dateText = "MAY 16",
        onClick = {}
    )