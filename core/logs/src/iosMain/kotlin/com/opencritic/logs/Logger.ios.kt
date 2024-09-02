package com.opencritic.logs

actual fun Logger(): Logger =
    NSLogLogger()