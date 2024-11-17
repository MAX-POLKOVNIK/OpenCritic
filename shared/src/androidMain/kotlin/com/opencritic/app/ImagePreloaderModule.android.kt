package com.opencritic.app

import com.opencritic.remote.images.CoilImagePreloader
import com.opencritic.remote.images.ImagePreloader
import org.koin.dsl.module

val imagePreloaderModule = module {
    factory<ImagePreloader> { CoilImagePreloader(get()) }
}