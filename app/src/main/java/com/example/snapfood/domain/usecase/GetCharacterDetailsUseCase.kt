package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.model.CharacterUiModel
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: String): Flow<Resources<CharacterUiModel>> =
        repository.getCharacterDetails(id)
}
