package com.opencritic.main.ui

import com.opencritic.mvvm.ScreenContent

data class MainContent(
    val currentTab: Tab<*>,
    val tabs: List<Tab<*>>,
    val onTabSelected: (Tab<*>) -> Unit,
) : ScreenContent