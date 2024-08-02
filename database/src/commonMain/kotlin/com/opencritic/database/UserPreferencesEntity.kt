package com.opencritic.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPreferencesEntity(
    @PrimaryKey val id: Int = 0,
    val authToken: String? = null,
    val isOfflineModeEnabled: Boolean = false,
)