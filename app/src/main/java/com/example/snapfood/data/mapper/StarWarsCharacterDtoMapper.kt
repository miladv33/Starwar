package com.example.snapfood.data.mapper

import com.example.snapfood.data.dto.StarWarsCharacterDto
import com.example.snapfood.domain.model.StarWarsCharacter
import jakarta.inject.Inject

class StarWarsCharacterDtoMapper @Inject constructor() {
    fun toDomain(dto: StarWarsCharacterDto): StarWarsCharacter {
        return StarWarsCharacter(
            id = dto.url.extractId(),
            name = dto.name,
            birthYear = dto.birthYear,
            height = dto.height,
            mass = dto.mass,
            hairColor = dto.hairColor,
            skinColor = dto.skinColor,
            eyeColor = dto.eyeColor,
            gender = dto.gender,
            homeWorld = dto.homeWorld,
            films = dto.films.map { it.extractId() },
            species = dto.species.map { it.extractId() },
            vehicles = dto.vehicles,
            starships = dto.starships
        )
    }

    private fun String.extractId(): String =
        this.trim('/').split("/").last()
}