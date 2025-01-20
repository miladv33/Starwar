package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.SpeciesInfo
import com.example.snapfood.domain.repository.SpeciesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSpeciesInfoUseCase @Inject constructor(
    private val repository: SpeciesRepository
) {
    suspend operator fun invoke(speciesId: String): Flow<Resources<SpeciesInfo>> =
        repository.getSpeciesInfo(speciesId)
}