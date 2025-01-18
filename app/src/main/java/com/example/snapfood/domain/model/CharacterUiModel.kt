package com.example.snapfood.domain.model

data class CharacterUiModel(
    val id: String,
    val name: String,
    val description: String
) {
    companion object {
        fun fromDomain(character: Character) = CharacterUiModel(
            id = character.id,
            name = character.name,
            description = character.description
        )
    }
}