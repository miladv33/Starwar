package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.SimpleCharacter

interface ICharacterRepository {
    suspend fun searchCharacters(query: String): List<SimpleCharacter>
    suspend fun getCharacterDetails(id: String): SimpleCharacter
}
