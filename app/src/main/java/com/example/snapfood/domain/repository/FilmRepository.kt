package com.example.snapfood.domain.repository

import com.example.snapfood.domain.model.FilmInfo
import com.example.snapfood.domain.model.Resources
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getFilmInfo(filmId: String): Flow<Resources<FilmInfo>>
}