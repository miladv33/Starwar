package com.example.snapfood.data.repository

import com.example.snapfood.data.api.StarWarsApi
import com.example.snapfood.data.mapper.StarWarsCharacterDtoMapper
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
private val api: StarWarsApi,
private val mapper: StarWarsCharacterDtoMapper
) : CharacterRepository {
    override suspend fun searchCharacters(query: String): Flow<Resources<List<StarWarsCharacter>>> {
        return flow {
            emit(Resources.Loading(true))
            try {
                val response = api.getPeople(query)
                val characters = response.results
                    .map { mapper.toDomain(it) }
                emit(Resources.Success(characters))
                emit(Resources.Loading(false))
            } catch (e: Exception) {
                emit(Resources.Error("Could not load data"))
                emit(Resources.Loading(false))
            }
        }
    }

    override suspend fun getCharacterDetails(characterId: String): Flow<Resources<StarWarsCharacter>> {
        return flow {
            emit(Resources.Loading(true))
            try {
                val response = api.getCharacterDetail(characterId)
                val character = mapper.toDomain(response)
                emit(Resources.Success(character))
                emit(Resources.Loading(false))
            } catch (e: Exception) {
                emit(Resources.Error("Could not load data"))
                emit(Resources.Loading(false))
            }
        }
    }
}