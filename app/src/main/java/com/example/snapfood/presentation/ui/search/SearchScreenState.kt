package com.example.snapfood.presentation.ui.search

import com.example.snapfood.domain.model.StarWarsCharacter

data class SearchScreenState(
    val searchQuery: String = "",
    val characters: List<StarWarsCharacter> = emptyList(),
    val isLoading: Boolean = false
)