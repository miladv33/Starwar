package com.example.snapfood.data.repository

import com.example.snapfood.domain.model.Character
import com.example.snapfood.domain.repository.ICharacterRepository

class CharacterRepository : ICharacterRepository {
    // TODO: Implement repository
    override suspend fun searchCharacters(query: String): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterDetails(id: String): Character {
        TODO("Not yet implemented")
    }
}
