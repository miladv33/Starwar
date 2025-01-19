package com.example.snapfood.data.repository

import com.example.snapfood.core.di.qualifier.Concrete
import com.example.snapfood.data.api.StarWarsApi
import com.example.snapfood.data.dto.Character
import com.example.snapfood.data.mapper.CharMapper
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.model.SimpleCharacter
import com.example.snapfood.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    @Concrete private val api: StarWarsApi,
    private val mapper: CharMapper
) :
    CharacterRepository {
    override suspend fun searchCharacters(query: String): Flow<Resources<List<SimpleCharacter>>> {
        return flow {
            emit(Resources.Loading(true))

            val result = try {
                api.getPeople(query)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
                null
            }
            if (result == null) {
                emit(Resources.Loading(false))
            }
            result?.results?.let { listing ->
                emit(Resources.Success(data = mapper.mapToChars(listing)))
                emit(Resources.Loading(false))
            }
        }
    }

    override suspend fun getCharacterDetails(characterId: String): Flow<Resources<SimpleCharacter>> {
        return flow {
            emit(Resources.Loading(true))

            val result = try {
                api.getCharacterDetail(characterId)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resources.Error("Could not load data"))
                null
            }
            if (result == null) {
                emit(Resources.Loading(false))
            }
            result?.let { char ->
                emit(Resources.Success(data = mapper.mapIntoCharacter(char)))
                emit(Resources.Loading(false))
            }
        }
    }
}
