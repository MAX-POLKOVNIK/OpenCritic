package com.opencritic.mvvm

import kotlinx.coroutines.flow.StateFlow

actual class ViewModelStateFlow<T : ViewModelState>(private val origin: StateFlow<T>) : StateFlow<T> by origin

actual fun <T : ViewModelState> StateFlow<T>.toViewModelStateFlow(): ViewModelStateFlow<T> =
    ViewModelStateFlow(this)