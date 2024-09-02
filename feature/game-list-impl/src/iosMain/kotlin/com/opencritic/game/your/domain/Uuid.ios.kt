package com.opencritic.game.your.domain

import platform.Foundation.NSUUID

actual fun uuidString(): String =
    NSUUID().UUIDString()