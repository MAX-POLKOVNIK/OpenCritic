package com.opencritic.database

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class YourGameEntity : RealmObject {
    @PrimaryKey
    var id: Long = 0
    var name: String = ""
    var isWanted: Boolean = false
    var isPlayed: Boolean = false
    var isFavorite: Boolean = false
    var isInterested: Boolean = false
}