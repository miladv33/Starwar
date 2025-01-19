package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.repository.CharacterRepository
import com.example.snapfood.domain.model.SimpleCharacter
import kotlinx.coroutines.flow.Flow

class GetCharacterDetailsUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: String): Flow<Resources<SimpleCharacter>> =
        repository.getCharacterDetails(id)
}
