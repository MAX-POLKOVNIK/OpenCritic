package com.opencritic.mvvm

import com.opencritic.resources.text.TextSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

abstract class BaseContentViewModel<Content> : BaseViewModel<CommonViewModelState<Content>>() {
    private val toastJob: Job? = null

    protected fun showToast(
        text: TextSource,
        duration: Duration = 3.seconds
    ) {
        if (toastJob != null) {
            toastJob.cancel()
        }

        scope.launch {
            mutableState.update {
                it.copy(
                    toast = Toast(text, duration)
                )
            }

            delay(duration)

            mutableState.update { it.copy(toast = null) }
        }
    }
}
