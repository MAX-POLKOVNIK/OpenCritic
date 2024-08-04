package com.opencritic.game.your.domain

sealed interface GameListAction {
    data object Add : GameListAction
    data object Remove : GameListAction
}