package com.example.snapfood.data.mapper

import com.example.snapfood.data.dto.Character
import com.example.snapfood.domain.model.CharacterUiModel
import javax.inject.Inject

class CharMapper @Inject constructor() {
    fun mapIntoCharacter(character: Character): CharacterUiModel {
        val id = character.url.trim('/').split("/").last()

        return CharacterUiModel(
            id = id,
            characterName = character.name,
            birthYear = character.birthYear,
            height = character.height,
            homeWorld = character.homeWorld,
            description = buildDescription(character)
        )
    }
    private fun buildDescription(character: Character): String {
        return "Height: ${character.height}cm, " +
                "Mass: ${character.mass}kg, " +
                "Hair: ${character.hairColor}, " +
                "Eyes: ${character.eyeColor}, " +
                "Birth Year: ${character.birthYear}"
    }
    fun mapToChars(list: List<Character>) = list.map { mapIntoCharacter(it) }
}