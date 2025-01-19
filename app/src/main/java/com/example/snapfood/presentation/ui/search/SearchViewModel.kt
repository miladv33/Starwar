package com.example.snapfood.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapfood.domain.model.CharacterUiModel
import com.example.snapfood.domain.model.SimpleCharacter
import com.example.snapfood.domain.usecase.SearchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchQueryChange -> {
                searchCharacters(event.query)
            }
            is SearchScreenEvent.OnCharacterClick -> {
                // This will be handled by the navigation in NavGraph
            }
        }
    }

    private fun searchCharacters(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(
                searchQuery = query,
                isLoading = true
            ) }

            try {
                val characters = searchCharactersUseCase(query)
                _state.update { it.copy(
                    characters = characters.map { character ->
                        CharacterUiModel(
                            id = character.id,
                            name = character.name,
                            description = character.description ?: "No description available"
                        )
                    },
                    isLoading = false
                ) }
            } catch (e: Exception) {
                // Handle error case
                _state.update { it.copy(
                    characters = emptyList(),
                    isLoading = false
                ) }
            }
        }
    }
}
data class SearchScreenState(
    val searchQuery: String = "",
    val characters: List<CharacterUiModel> = emptyList(),
    val isLoading: Boolean = false
)

sealed class SearchScreenEvent {
    data class OnSearchQueryChange(val query: String) : SearchScreenEvent()
    data class OnCharacterClick(val characterId: String) : SearchScreenEvent()
}