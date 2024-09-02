package com.opencritic.app

import com.opencritic.navigation.ScreenCreator
import com.opencritic.navigation.ScreenCreators
import org.koin.dsl.module

fun screenCreatorsModule(creators: List<ScreenCreator<*>>) =
    module {
        single { ScreenCreators(creators) }
    }