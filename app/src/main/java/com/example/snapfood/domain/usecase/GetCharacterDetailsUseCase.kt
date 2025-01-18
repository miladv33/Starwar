package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.repository.ICharacterRepository
import com.example.snapfood.domain.model.Character

class GetCharacterDetailsUseCase(
    private val repository: ICharacterRepository
) {
    suspend operator fun invoke(id: String): Character =
        repository.getCharacterDetails(id)
}
