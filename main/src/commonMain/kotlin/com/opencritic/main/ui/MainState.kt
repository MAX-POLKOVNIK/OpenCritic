package com.opencritic.main.ui

import com.opencritic.mvvm.ViewModelState

data class MainState(
    val tabs: List<Tab>,
) : ViewModelState