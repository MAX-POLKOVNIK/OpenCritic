package com.opencritic.game.your.domain

import java.util.UUID

actual fun uuidString(): String =
    UUID.randomUUID().toString()