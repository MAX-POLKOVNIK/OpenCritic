package com.opencritic.dashboard.ui

import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class DashboardTitleListItem(
    private val title: TextSource,
    private val subtitle: TextSource?,
    val buttonTitle: TextSource? = null,
    val onButtonClick: () -> Unit = {},
) {
    val titleText: TextSource = title

    val subtitleText: TextSource = subtitle ?: "".asTextSource()

    val isDescriptionVisible: Boolean = subtitle != null
}