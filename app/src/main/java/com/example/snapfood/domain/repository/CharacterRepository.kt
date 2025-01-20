package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.domain.model.Resources
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun searchCharacters(query: String): Flow<Resources<List<StarWarsCharacter>>>
    suspend fun getCharacterDetails(characterId: String): Flow<Resources<StarWarsCharacter>>
}
