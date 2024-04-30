package com.opencritic.dashboard.ui

data class DashboardTitleListItem(
    val title: String,
    val subtitle: String?,
) {
    val titleText: String
        get() = title

    val subtitleText: String
        get() = subtitle ?: ""

    val isDescriptionVisible: Boolean
        get() = !subtitle.isNullOrBlank()
}