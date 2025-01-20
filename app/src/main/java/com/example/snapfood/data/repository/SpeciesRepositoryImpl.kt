package com.example.snapfood.data.repository

import com.example.snapfood.data.api.StarWarsApi
import com.example.snapfood.data.mapper.FilmDtoMapper
import com.example.snapfood.data.mapper.SpeciesDtoMapper
import com.example.snapfood.domain.model.FilmInfo
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.SpeciesInfo
import com.example.snapfood.domain.repository.FilmRepository
import com.example.snapfood.domain.repository.SpeciesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpeciesRepositoryImpl @Inject constructor(
    private val api: StarWarsApi,
    private val mapper: SpeciesDtoMapper
) : SpeciesRepository {
    override suspend fun getSpeciesInfo(speciesId: String): Flow<Resources<SpeciesInfo>> = flow {
        emit(Resources.Loading(true))
        try {
            val response = api.getSpecies(speciesId)
            val speciesInfo = mapper.toDomain(response, speciesId)
            emit(Resources.Success(speciesInfo))
        } catch (e: Exception) {
            emit(Resources.Error("Could not load species data"))
        } finally {
            emit(Resources.Loading(false))
        }
    }
}
