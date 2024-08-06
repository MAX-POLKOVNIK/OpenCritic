package com.opencritic.api.dto.calendar

import com.opencritic.api.dto.image.ImagesDto
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CalendarGameDto(
    val images: ImagesDto = ImagesDto(),
    val name: String,
    val id: Long,
    val firstReleaseDate: Instant,
)