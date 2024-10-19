package com.opencritic.database.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameInListEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val posterUrl: String,
    val tier: GameTier?,
    val score: Int,
)