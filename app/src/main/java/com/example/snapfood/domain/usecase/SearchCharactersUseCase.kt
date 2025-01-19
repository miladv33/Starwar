package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.repository.CharacterRepository
import com.example.snapfood.domain.model.SimpleCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(query: String): Flow<Resources<List<SimpleCharacter>>> =
        repository.searchCharacters(query)
}
