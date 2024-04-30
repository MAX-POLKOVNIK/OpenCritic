package com.opencritic.resources

import org.koin.dsl.module

fun resourcesModule(
    strings: StringResourceProvider,
    images: ImageResourceProvider,
) = module {
    single<StringResourceProvider> { strings }
    single<ImageResourceProvider> { images }
}