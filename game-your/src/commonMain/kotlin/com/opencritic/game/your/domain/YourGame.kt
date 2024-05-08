package com.opencritic.game.your.domain

data class YourGame(
    val id: Long,
    val name: String,
    val isWanted: Boolean,
    val isPlayed: Boolean,
    val isFavorite: Boolean,
) {
    val isInterested: Boolean =
        isWanted || isPlayed || isFavorite

    fun actioned(action: YourGameAction): YourGame =
        when (action) {
            YourGameAction.Want ->
                if (isWanted) unwanted() else wanted()
            YourGameAction.Played ->
                if (isPlayed) notPlayed() else played()
            YourGameAction.Favorite ->
                if (isFavorite) notFavorite() else favorite()
        }

    fun wanted(): YourGame =
        copy(
            isWanted = true,
            isPlayed = false,
        )

    fun played(): YourGame =
        copy(
            isWanted = false,
            isPlayed = true,
        )

    fun favorite(): YourGame =
        copy(
            isFavorite = true,
        )

    fun unwanted(): YourGame =
        copy(
            isWanted = false,
        )

    fun notPlayed(): YourGame =
        copy(
            isPlayed = false,
        )

    fun notFavorite(): YourGame =
        copy(
            isFavorite = false,
        )
}