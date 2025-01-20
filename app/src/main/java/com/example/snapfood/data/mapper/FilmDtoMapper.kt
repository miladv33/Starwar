package com.example.snapfood.data.mapper

import com.example.snapfood.data.dto.Film
import com.example.snapfood.domain.model.FilmInfo
import jakarta.inject.Inject

class FilmDtoMapper @Inject constructor() {
    fun toDomain(dto: Film, filmId: String): FilmInfo {
        return FilmInfo(
            id = filmId,
            title = dto.title,
            openingCrawl = dto.openingCrawl
        )
    }
}