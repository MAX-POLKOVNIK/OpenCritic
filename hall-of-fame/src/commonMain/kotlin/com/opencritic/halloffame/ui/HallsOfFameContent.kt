package com.opencritic.halloffame.ui

import com.opencritic.mvvm.ScreenContent

data class HallsOfFameContent(
    val lists: List<HallOfFameGameListListItem>
) : ScreenContent