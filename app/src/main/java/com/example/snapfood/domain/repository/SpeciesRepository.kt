package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.SpeciesInfo
import kotlinx.coroutines.flow.Flow

interface SpeciesRepository {
    suspend fun getSpeciesInfo(speciesId: String): Flow<Resources<SpeciesInfo>>
}