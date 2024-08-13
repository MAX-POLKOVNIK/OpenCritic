package com.opencritic.database.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameListEntity(
    @PrimaryKey val id: String,
    val name: String,
    val isVital: Boolean,
    val vitalType: VitalType?,
)