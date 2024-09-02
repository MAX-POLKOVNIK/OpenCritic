package com.opencritic.api.dto.game

sealed class GameTimeKey(val queryKey: String) {
    data class Year(val year: Int) : GameTimeKey(year.toString())
    data object AllTime : GameTimeKey("")
    data object Last90 : GameTimeKey("last90")
    data object Upcoming : GameTimeKey("upcoming")
}