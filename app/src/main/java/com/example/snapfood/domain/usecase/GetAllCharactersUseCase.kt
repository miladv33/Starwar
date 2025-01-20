package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.domain.repository.AllCharactersRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase @Inject constructor(
    private val repository: AllCharactersRepository
) {
    suspend operator fun invoke(): Flow<Resources<List<StarWarsCharacter>>> =
        repository.getAllCharacters()
}