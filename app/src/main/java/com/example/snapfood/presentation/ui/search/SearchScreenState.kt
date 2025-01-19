package com.example.snapfood.presentation.ui.search

import com.example.snapfood.domain.model.CharacterUiModel

data class SearchScreenState(
    val searchQuery: String = "",
    val characters: List<CharacterUiModel> = emptyList(),
    val isLoading: Boolean = false
)