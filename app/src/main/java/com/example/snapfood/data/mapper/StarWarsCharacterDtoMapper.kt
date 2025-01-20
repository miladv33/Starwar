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
            species = dto.species,
            vehicles = dto.vehicles,
            starships = dto.starships
        )
    }

    fun toCharacterUiModel(character: StarWarsCharacter): StarWarsCharacter {
        return StarWarsCharacter(
            id = character.id,
            birthYear = character.birthYear,
            height = character.height,
            mass = character.mass,
            hairColor = character.hairColor,
            eyeColor = character.eyeColor,
            name = character.name,
            films = character.films,
            species = character.species,
            vehicles = character.vehicles,
            starships = character.starships,
            gender = character.gender,
            homeWorld = character.homeWorld,
            skinColor = character.skinColor,
        )
    }

    private fun buildDescription(character: StarWarsCharacter): String {
        return buildString {
            append("Height: ${character.height}cm, ")
            append("Mass: ${character.mass}kg, ")
            append("Hair: ${character.hairColor}, ")
            append("Eyes: ${character.eyeColor}, ")
            append("Birth Year: ${character.birthYear}")
        }
    }

    private fun String.extractId(): String =
        this.trim('/').split("/").last()
}