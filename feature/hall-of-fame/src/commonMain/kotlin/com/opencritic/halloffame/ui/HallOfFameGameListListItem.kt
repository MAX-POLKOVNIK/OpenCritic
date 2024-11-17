package com.opencritic.halloffame.ui

import com.opencritic.mvvm.ListItem
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HallOfFameGameListListItem(
    val year: Int,
    val yearText: TextSource = StringRes.str_hall_of_fame_year_formatted.asTextSource(year),
    val games: ImmutableList<HallOfFameGameListItem>,
    override val id: Int = year,
) : ListItem<Int>

@Suppress("FunctionName")
fun HallOfFameGameListListItem_PreviewData(): HallOfFameGameListListItem =
    HallOfFameGameListListItem(
        year = 2024,
        games = persistentListOf(
            HallOfFameGameListItem_PreviewData()
        )
    )