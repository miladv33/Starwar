package com.example.snapfood.presentation.ui.search

import androidx.lifecycle.ViewModel
import com.example.snapfood.domain.usecase.SearchCharactersUseCase

class SearchViewModel(
    private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {
    // TODO: Implement ViewModel
}

data class SearchScreenState(
    val searchQuery: String = "",
    val characters: List<CharacterItem> = emptyList(),
    val isLoading: Boolean = false
)

sealed class SearchScreenEvent {
    data class OnSearchQueryChange(val query: String) : SearchScreenEvent()
    data class OnCharacterClick(val characterId: String) : SearchScreenEvent()
}