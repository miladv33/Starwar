package com.example.snapfood.data.mapper

import com.example.snapfood.data.dto.Character
import com.example.snapfood.domain.model.SimpleCharacter

class CharMapper {
    fun mapIntoCharacter(character: Character) = SimpleCharacter(character.url, character.name, character.url)
}