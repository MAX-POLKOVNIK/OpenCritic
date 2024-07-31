package com.opencritic.resources

import org.koin.dsl.module

fun resourcesModule(
    strings: StringProvider,
    dateFormatter: DateFormatter,
) = module {
    single<StringProvider> { strings }
    single<DateFormatter> { dateFormatter }
}