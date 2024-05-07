package com.opencritic.mvvm

abstract class BaseErrorState(open val message: String, val action: (() -> Unit)? = null) {
    constructor(throwable: Throwable, action: () -> Unit = {}) : this(throwable.toString(), action)
}