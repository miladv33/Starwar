package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.CharacterUiModel
import com.example.snapfood.domain.model.Resources
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun searchCharacters(query: String): Flow<Resources<List<CharacterUiModel>>>
    suspend fun getCharacterDetails(characterId: String): Flow<Resources<CharacterUiModel>>
}
