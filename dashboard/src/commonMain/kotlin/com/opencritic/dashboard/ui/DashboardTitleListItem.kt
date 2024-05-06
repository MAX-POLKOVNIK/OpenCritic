package com.opencritic.dashboard.ui

data class DashboardTitleListItem(
    private val title: String,
    private val subtitle: String?,
) {
    val titleText: String
        get() = title

    val subtitleText: String
        get() = subtitle ?: ""

    val isDescriptionVisible: Boolean
        get() = !subtitle.isNullOrBlank()
}