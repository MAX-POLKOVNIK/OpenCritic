package com.opencritic.dashboard.ui

import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class DashboardTitleListItem(
    private val title: TextSource,
    private val subtitle: TextSource?,
) {
    val titleText: TextSource
        get() = title

    val subtitleText: TextSource
        get() = subtitle ?: "".asTextSource()

    val isDescriptionVisible: Boolean
        get() = subtitle != null
}