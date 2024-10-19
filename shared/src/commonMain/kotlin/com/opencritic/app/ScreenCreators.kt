package com.opencritic.app

import com.opencritic.navigation.ScreenCreators
import org.koin.mp.KoinPlatformTools

fun ScreenCreators.Companion.registered(): ScreenCreators =
    KoinPlatformTools.defaultContext().get().get(ScreenCreators::class)