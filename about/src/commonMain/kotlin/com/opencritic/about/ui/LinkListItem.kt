package com.opencritic.about.ui

import com.opencritic.resources.text.TextSource

data class LinkListItem(
    val title: TextSource,
    val url: String,
    private val onClick: (String) -> Unit,
) {
    fun onClick() = onClick(url)
}