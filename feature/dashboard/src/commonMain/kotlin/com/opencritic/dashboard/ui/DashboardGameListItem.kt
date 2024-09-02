package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameItem
import com.opencritic.games.GameRankModel
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.text.DateTextSource
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import com.opencritic.resources.text.format
import com.opencritic.resources.images.SharedImages
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class DashboardGameListItem(
    override val id: Long,
    val rank: GameRankModel?,
    val nameText: String,
    val dateText: TextSource,
    private val onClick: (DashboardGameListItem) -> Unit,
) : ListItem<Long> {
    constructor(
        gameItem: GameItem,
        onClick: (DashboardGameListItem) -> Unit,
    ) : this(
        id = gameItem.id,
        rank = GameRankModel(gameItem.rank),
        nameText = gameItem.name,
        dateText = gameItem.releaseDate.toLocalDateTime(TimeZone.UTC).date format DateTextSource.Format.Short,
        onClick = onClick,
    )

    fun click() = onClick(this)
}

@Suppress("FunctionName")
fun DashboardGameListItem_PreviewData(): DashboardGameListItem =
    DashboardGameListItem(
        id = 1,
        rank = GameRankModel(headImageResource = SharedImages.fairHead, scoreText = "15"),
        nameText = "Game ldskfhds  sdkjhfjsd",
        dateText = "MAY 16".asTextSource(),
        onClick = {}
    )