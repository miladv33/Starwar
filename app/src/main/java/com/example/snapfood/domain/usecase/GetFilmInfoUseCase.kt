package com.example.snapfood.domain.usecase

import com.example.snapfood.domain.model.FilmInfo
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.repository.FilmRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetFilmInfoUseCase @Inject constructor(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(filmId: String): Flow<Resources<FilmInfo>> =
        repository.getFilmInfo(filmId)
}