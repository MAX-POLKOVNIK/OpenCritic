package com.opencritic.mvvm

import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

abstract class BaseContentViewModel<Content> : BaseViewModel<CommonViewModelState<Content>>() {
    private var toastJob: Job? = null

    protected fun showToast(
        text: String,
        duration: Duration = 3.seconds
    ) = showToast(
        text = text.asTextSource(),
        duration = duration,
    )

    protected fun showToast(
        text: TextSource,
        duration: Duration = 3.seconds
    ) {
        toastJob?.cancel()

        toastJob = scope.launch {
            mutableState.update {
                it.setToast(text)
            }

            delay(duration)

            mutableState.update { it.clearToast() }
        }
    }
}
