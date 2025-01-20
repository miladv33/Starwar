package com.example.snapfood.data.repository

import com.example.snapfood.data.api.StarWarsApi
import com.example.snapfood.data.mapper.StarWarsCharacterDtoMapper
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.domain.repository.AllCharactersRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AllCharactersRepositoryImpl @Inject constructor(
    private val api: StarWarsApi,
    private val mapper: StarWarsCharacterDtoMapper
) : AllCharactersRepository {
    override suspend fun getAllCharacters(): Flow<Resources<List<StarWarsCharacter>>> {
        return flow {
            emit(Resources.Loading(true))
            try {
                val response = api.getAllPeople()
                val characters = response.results
                    .map { mapper.toDomain(it) }
                    .map { mapper.toCharacterUiModel(it) }
                emit(Resources.Success(characters))
                emit(Resources.Loading(false))
            } catch (e: Exception) {
                emit(Resources.Error("Could not load characters"))
                emit(Resources.Loading(false))
            }
        }
    }
}