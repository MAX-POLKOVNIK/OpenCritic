package com.opencritic.main.ui

import com.opencritic.mvvm.ScreenContent
import kotlinx.collections.immutable.ImmutableList

data class MainContent(
    val currentTab: Tab<*>,
    val tabs: ImmutableList<Tab<*>>,
    val onTabSelected: (Tab<*>) -> Unit,
) : ScreenContent