package com.opencritic.mvvm

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opencritic.navigation.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<State : ViewModelState> : ViewModel() {

    protected val mutableState: MutableStateFlow<State> by lazy {
        MutableStateFlow(initialState())
            .also { onStateInit() }
    }

    val state: ViewModelStateFlow<State> by lazy { mutableState.toViewModelStateFlow() }

    protected val scope: CoroutineScope
        get() = viewModelScope

    private var router: Router? = null

    protected abstract fun initialState(): State

    @CallSuper
    open fun onStateInit() {}

    fun setRouter(router: Router) {
        this.router = router
    }

    fun requireRouter(): Router =
        requireNotNull(router) { "Router is not set" }
}