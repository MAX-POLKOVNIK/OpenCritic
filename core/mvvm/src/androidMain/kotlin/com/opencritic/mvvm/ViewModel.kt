package com.opencritic.mvvm

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
inline fun <reified T : ViewModel, InitArgs : Any> viewModel(args: InitArgs): T =
    koinViewModel { parametersOf(args) }