package com.opencritic.auth.ui

import com.opencritic.mvvm.ScreenContent
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class AuthContent(
    val token: String,
    val tokenHint: TextSource,
    val descriptionText: TextSource,
    val authButtonText: TextSource,
    val onAuthButtonClicked: () -> Unit,
    val onTokenChanged: (String) -> Unit,
) : ScreenContent

@Suppress("FunctionName")
fun AuthContent_PreviewData(): AuthContent =
    AuthContent(
        token = "kfjhgsdjhfjkdshfjksdhfjhsdfjhsdkjfhajshfkjadshfjasdhfkjhsdfkjhasdfjkhasdjfhsdkjhfsdh",
        tokenHint = "Token".asTextSource(),
        descriptionText = "Some very long description. skdghsdkhf lksdfhklsadhf asdlkfhsdkalhf asdlkfhsdaklh asdlkfhsdakhf".asTextSource(),
        authButtonText = "Auth".asTextSource(),
        onTokenChanged = {},
        onAuthButtonClicked = {}
    )