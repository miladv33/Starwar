package com.example.snapfood.presentation.ui.details

import com.example.snapfood.domain.model.CharacterUiModel

data class DetailScreenState (
    val searchQuery: String = "",
    val characters: List<CharacterUiModel> = emptyList(),
    val isLoading: Boolean = false
)