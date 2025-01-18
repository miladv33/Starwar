package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.repository.ICharacterRepository
import com.example.snapfood.domain.model.Character

class SearchCharactersUseCase(
    private val repository: ICharacterRepository
) {
    suspend operator fun invoke(query: String): List<Character> =
        repository.searchCharacters(query)
}
