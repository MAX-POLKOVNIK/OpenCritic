package com.opencritic.app

import com.opencritic.remote.images.ImagePreloader
import org.koin.dsl.module

fun imagePreloaderModule(block: () -> ImagePreloader) = module {
    factory<ImagePreloader> { block() }
}