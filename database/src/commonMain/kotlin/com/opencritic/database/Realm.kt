package com.opencritic.database

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

fun createRealmConfiguration(): RealmConfiguration =
    RealmConfiguration.create(
        schema = setOf(
            YourGameEntity::class,
        ),
    )

fun createRealm(): Realm =
    Realm.open(createRealmConfiguration())