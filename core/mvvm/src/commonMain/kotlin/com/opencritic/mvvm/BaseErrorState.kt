package com.opencritic.mvvm

import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

abstract class BaseErrorState(
    open val message: TextSource,
    open val actionText: TextSource = StringRes.str_retry.asTextSource(),
    open val action: () -> Unit = {},
)