package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.repository.ICharacterRepository
import com.example.snapfood.domain.model.SimpleCharacter

class SearchCharactersUseCase(
    private val repository: ICharacterRepository
) {
    suspend operator fun invoke(query: String): List<SimpleCharacter> =
        repository.searchCharacters(query)
}
