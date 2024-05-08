package com.opencritic.resources

import org.koin.dsl.module

fun resourcesModule(
    strings: StringProvider,
    images: ImageResourceProvider,
    dateFormatter: DateFormatter,
) = module {
    single<StringProvider> { strings }
    single<ImageResourceProvider> { images }
    single<DateFormatter> { dateFormatter }
}