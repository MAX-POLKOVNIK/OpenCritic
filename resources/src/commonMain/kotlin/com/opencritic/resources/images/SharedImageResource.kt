package com.opencritic.resources.images

import dev.icerock.moko.resources.ImageResource

data class SharedImageResource(
    val imageRes: ImageResource
)

internal fun ImageResource.shared(): SharedImageResource =
    SharedImageResource(this)