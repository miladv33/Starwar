package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.repository.ICharacterRepository
import com.example.snapfood.domain.model.SimpleCharacter

class GetCharacterDetailsUseCase(
    private val repository: ICharacterRepository
) {
    suspend operator fun invoke(id: String): SimpleCharacter =
        repository.getCharacterDetails(id)
}
