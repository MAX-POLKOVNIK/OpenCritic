package com.opencritic.halloffame.ui

import com.opencritic.mvvm.ListItem
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class HallOfFameGameListListItem(
    val year: Int,
    val yearText: TextSource = StringRes.str_hall_of_fame_year_formatted.asTextSource(year),
    val games: List<HallOfFameGameListItem>,
    override val id: Int = year,
) : ListItem<Int>

@Suppress("FunctionName")
fun HallOfFameGameListListItem_PreviewData(): HallOfFameGameListListItem =
    HallOfFameGameListListItem(
        year = 2024,
        games = listOf(
            HallOfFameGameListItem_PreviewData()
        )
    )