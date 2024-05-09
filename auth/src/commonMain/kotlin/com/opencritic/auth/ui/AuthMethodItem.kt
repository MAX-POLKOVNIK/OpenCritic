package com.opencritic.auth.ui

import com.opencritic.resources.ImageResource

data class AuthMethodItem(
    val imageResource: ImageResource,
    val text: String,
    val onClick: () -> Unit,
)