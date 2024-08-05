package com.opencritic.api.dto.calendar

import com.opencritic.api.dto.image.ImageLinksDto
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CalendarGameDto(
    val images: ImageLinksDto = ImageLinksDto(),
    val name: String,
    val id: Long,
    val firstReleaseDate: Instant,
)