package com.opencritic.mvvm

import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class ErrorState(
    override val message: TextSource,
    override val actionText: TextSource = "Retry".asTextSource(),
    override val action: () -> Unit = {},
) : BaseErrorState(message, actionText, action)