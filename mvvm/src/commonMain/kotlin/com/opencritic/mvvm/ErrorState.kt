package com.opencritic.mvvm

data class ErrorState(
    override val message: String,
    override val actionText: String? = "Retry",
    override val action: (() -> Unit)? = null,
) : BaseErrorState(message, actionText, action)