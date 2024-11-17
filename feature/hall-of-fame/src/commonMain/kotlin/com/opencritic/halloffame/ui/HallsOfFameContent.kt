package com.opencritic.halloffame.ui

import com.opencritic.mvvm.ScreenContent
import kotlinx.collections.immutable.ImmutableList

data class HallsOfFameContent(
    val lists: ImmutableList<HallOfFameGameListListItem>
) : ScreenContent