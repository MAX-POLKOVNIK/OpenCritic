package com.opencritic.api.dto.image

fun String.prefixedImageUrl(): String =
    "https://img.opencritic.com/$this"