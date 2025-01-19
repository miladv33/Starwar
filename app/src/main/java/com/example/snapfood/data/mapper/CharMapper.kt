package com.example.snapfood.data.mapper

import com.example.snapfood.data.dto.Character
import com.example.snapfood.domain.model.SimpleCharacter
import javax.inject.Inject

class CharMapper @Inject constructor() {
    fun mapIntoCharacter(character: Character) = SimpleCharacter(character.url, character.name, character.url)
    fun mapToChars(list: List<Character>) = list.map { mapIntoCharacter(it) }
}