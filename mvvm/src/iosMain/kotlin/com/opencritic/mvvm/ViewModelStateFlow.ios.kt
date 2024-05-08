package com.opencritic.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

actual class ViewModelStateFlow<T : ViewModelState>(private val origin: StateFlow<T>) : StateFlow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return Closeable { job.cancel() }
    }

    fun interface Closeable {
        fun close()
    }
}

actual fun <T : ViewModelState> StateFlow<T>.toViewModelStateFlow(): ViewModelStateFlow<T> =
    ViewModelStateFlow(this)