package com.example.snapfood.data.mapper

import com.example.snapfood.data.dto.Species
import com.example.snapfood.domain.model.SpeciesInfo
import jakarta.inject.Inject

class SpeciesDtoMapper @Inject constructor() {
    fun toDomain(dto: Species, speciesId: String): SpeciesInfo {
        return SpeciesInfo(
            id = speciesId,
            name = dto.name,
            classification = dto.classification,
            language = dto.language,
            designation = dto.designation,
            averageLifespan = dto.averageLifespan
        )
    }
}