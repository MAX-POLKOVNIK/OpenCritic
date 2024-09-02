package com.opencritic.database.list

import androidx.room.Entity

@Entity(primaryKeys = ["listId", "gameId"])
data class ListGameRelationEntity(
    val listId: String,
    val gameId: Long,
)