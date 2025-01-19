package com.example.snapfood.domain.model

import com.example.snapfood.data.dto.Character
import okhttp3.internal.notifyAll

data class CharacterUiModel(
    val id: String,
    val name: String,
    val description: String
) {
    companion object {
        fun fromDomain(characters: List<Character>) = characters.map {
            CharacterUiModel(
                id = it.id,
                name = it.name,
                description = it.name
            )
        }
    }
}