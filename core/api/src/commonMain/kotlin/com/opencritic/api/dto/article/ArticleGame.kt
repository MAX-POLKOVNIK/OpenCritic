package com.opencritic.api.dto.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleGame(
    val id: Long,
    val name: String,
)