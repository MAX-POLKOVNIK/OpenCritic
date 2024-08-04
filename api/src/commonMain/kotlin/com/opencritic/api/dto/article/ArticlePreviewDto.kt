package com.opencritic.api.dto.article

import com.opencritic.api.dto.review.AuthorDto
import com.opencritic.api.dto.review.OutletDto
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlePreviewDto(
    val id: Long,
    val imageV2: ArticleImagesDto,
    val title: String = "",
    val type: String = "",
    @SerialName("Authors") val authors: List<AuthorDto> = emptyList(),
    val teaser: String,
    val description: String,
    val publishedDate: Instant,
    @SerialName("Outlet") val outlet: OutletDto? = null,
    val syndicatedAuthor: String? = null
)