package com.opencritic.api.dto.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleImagesDto(
    val og: String = "",
    val th: String = "",
    val xs: String = "",
    val sm: String = "",
    val md: String = "",
    val lg: String = "",
    val xl: String = "",
    val xxl: String = "",
)