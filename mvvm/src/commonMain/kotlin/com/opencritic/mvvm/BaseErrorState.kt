package com.opencritic.mvvm

abstract class BaseErrorState(
    open val message: String,
    open val actionText: String? = "Retry",
    open val action: (() -> Unit)? = null
)