package com.example.snapfood.data.repository

import com.example.snapfood.domain.model.SimpleCharacter
import com.example.snapfood.domain.repository.ICharacterRepository
import javax.inject.Inject

class CharacterRepository @Inject constructor(
) : ICharacterRepository {
    override suspend fun searchCharacters(query: String): List<SimpleCharacter> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterDetails(id: String): SimpleCharacter {
        TODO("Not yet implemented")
    }
}