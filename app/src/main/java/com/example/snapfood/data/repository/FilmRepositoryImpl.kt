package com.example.snapfood.data.repository

import com.example.snapfood.data.api.StarWarsApi
import com.example.snapfood.data.mapper.FilmDtoMapper
import com.example.snapfood.domain.model.FilmInfo
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.repository.FilmRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepositoryImpl @Inject constructor(
    private val api: StarWarsApi,
    private val mapper: FilmDtoMapper
) : FilmRepository {
    override suspend fun getFilmInfo(filmId: String): Flow<Resources<FilmInfo>> = flow {
        emit(Resources.Loading(true))
        try {
            val response = api.getFilm(filmId)
            val filmInfo = mapper.toDomain(response, filmId)
            emit(Resources.Success(filmInfo))
        } catch (e: Exception) {
            emit(Resources.Error("Could not load film data"))
        } finally {
            emit(Resources.Loading(false))
        }
    }
}