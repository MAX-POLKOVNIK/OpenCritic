package com.opencritic.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class YourGameEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val isWanted: Boolean,
    val isPlayed: Boolean,
    val isFavorite: Boolean,
    val isInterested: Boolean,
)