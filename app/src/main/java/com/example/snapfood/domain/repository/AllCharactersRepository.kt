package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.StarWarsCharacter
import kotlinx.coroutines.flow.Flow

interface AllCharactersRepository {
    suspend fun getAllCharacters(): Flow<Resources<List<StarWarsCharacter>>>
}