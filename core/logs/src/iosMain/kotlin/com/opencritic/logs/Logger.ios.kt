package com.opencritic.logs

actual fun Logger(): Logger =
    OsLogLogger()