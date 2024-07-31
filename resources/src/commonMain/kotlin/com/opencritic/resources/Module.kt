package com.opencritic.resources

import org.koin.dsl.module

fun resourcesModule(
    strings: StringProvider,
) = module {
    single<StringProvider> { strings }
}