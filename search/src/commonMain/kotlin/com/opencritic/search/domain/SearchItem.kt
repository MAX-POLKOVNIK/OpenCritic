package com.opencritic.search.domain

data class SearchItem(
    val id: Long,
    val kind: SearchItemKind,
    val name: String,
)