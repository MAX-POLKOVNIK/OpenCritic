package com.opencritic.game.your.domain

sealed interface GameListId {
    data object Want : GameListId
    data object Played : GameListId
    data object Favorite : GameListId
    data class Custom(val id: String) : GameListId
}