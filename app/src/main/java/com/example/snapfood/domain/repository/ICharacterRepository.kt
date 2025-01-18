package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.Character

interface ICharacterRepository {
    suspend fun searchCharacters(query: String): List<Character>
    suspend fun getCharacterDetails(id: String): Character
}
