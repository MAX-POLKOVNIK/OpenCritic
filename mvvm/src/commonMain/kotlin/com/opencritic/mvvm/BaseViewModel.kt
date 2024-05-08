package com.opencritic.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opencritic.navigation.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<State : ViewModelState> : ViewModel() {

    abstract val initialState: State

    protected val mutableState: MutableStateFlow<State> by lazy { MutableStateFlow(initialState) }

    val state: ViewModelStateFlow<State> by lazy { mutableState.toViewModelStateFlow() }

    val scope: CoroutineScope
        get() = viewModelScope

    private var router: Router? = null

    fun setRouter(router: Router) {
        this.router = router
    }

    fun requireRouter(): Router =
        requireNotNull(router) { "Router is not set" }
}