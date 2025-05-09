package com.example.snapfood.presentation.ui.details

import com.example.snapfood.domain.model.FilmInfo
import com.example.snapfood.domain.model.SpeciesInfo
import com.example.snapfood.domain.model.StarWarsCharacter


data class DetailScreenState(
    val characterId: String = "",
    val character: StarWarsCharacter? = null,
    val films: Map<String, FilmInfo> = emptyMap(),
    val species: Map<String, SpeciesInfo> = emptyMap(),
    val isLoading: Boolean = false,
    val loadingFilms: Set<String> = emptySet(),
    val loadingSpecies: Set<String> = emptySet(),
    val errorFilms: Map<String, String> = emptyMap(),
    val errorSpecies: Map<String, String> = emptyMap()
)