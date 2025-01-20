package com.example.snapfood.presentation.ui.details

import com.example.snapfood.domain.model.StarWarsCharacter


data class DetailScreenState(
    val characterId: String = "",
    val character: StarWarsCharacter? = null,
    val isLoading: Boolean = false,
)
