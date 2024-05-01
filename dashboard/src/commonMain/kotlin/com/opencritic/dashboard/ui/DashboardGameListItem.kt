package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameItem
import com.opencritic.games.GameRank
import com.opencritic.games.GameRankModel
import com.opencritic.mvvm.ListItem
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
        onClick: (DashboardGameListItem) -> Unit,
    ) : this(
        id = gameItem.id,
        rank = GameRankModel(imageResourceProvider, gameItem.rank),
        nameText = gameItem.name,
        dateText = gameItem.releaseDate.format(),
        onClick = onClick,
    )
}

@OptIn(FormatStringsInDatetimeFormats::class)
val dateTimeFormat = LocalDate.Format {
    byUnicodePattern("MM dd")
}

private fun Instant.format(): String =
    toLocalDateTime(TimeZone.UTC)
        .date
        .format(dateTimeFormat)