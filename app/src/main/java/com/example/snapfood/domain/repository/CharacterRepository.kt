package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.SimpleCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun searchCharacters(query: String): Flow<Resources<List<SimpleCharacter>>>
    suspend fun getCharacterDetails(characterId: String): Flow<Resources<SimpleCharacter>>
}
