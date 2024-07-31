package com.opencritic.auth.ui

import com.opencritic.resources.images.SharedImageResource

data class AuthMethodItem(
    val imageResource: SharedImageResource,
    val text: String,
    val onClick: () -> Unit,
)