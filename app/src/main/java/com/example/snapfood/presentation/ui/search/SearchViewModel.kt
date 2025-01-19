package com.example.snapfood.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapfood.domain.model.CharacterUiModel
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.usecase.SearchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()
    private var searchJob: Job? = null

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchQueryChange -> updateSearchQuery(event.query)
        }
    }

    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch { searchCharacters() }
    }

    private suspend fun searchCharacters() {
        val currentQuery = state.value.searchQuery

        if (currentQuery.isBlank()) {
            clearSearchResults()
            return
        }

        searchCharactersUseCase(currentQuery)
            .onStart { setLoading(true) }
            .catch { error ->
                handleError(error)
            }
            .collect { result ->
                handleSearchResult(result)
            }
    }

    private fun clearSearchResults() {
        _state.update {
            it.copy(
                characters = emptyList(),
                isLoading = false,
            )
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }

    private fun handleError(error: Throwable) {
        _state.update {
            it.copy(
                isLoading = false,
                characters = emptyList(),
            )
        }
    }

    private fun handleSearchResult(result: Resources<List<CharacterUiModel>>) {
        when (result) {
            is Resources.Success -> updateCharactersList(result.data)
            is Resources.Error -> handleSearchError()
            is Resources.Loading -> setLoading(result.isLoading)
        }
    }

    private fun updateCharactersList(characters: List<CharacterUiModel>?) {
        _state.update { currentState ->
            currentState.copy(
                characters = characters?.map { it.toUiModel() } ?: emptyList(),
                isLoading = false,
            )
        }
    }

    private fun handleSearchError() {
        _state.update {
            it.copy(
                isLoading = false,
                characters = emptyList(),
            )
        }
    }
}

// Extension function to map domain model to UI model
private fun CharacterUiModel.toUiModel() = CharacterUiModel(
    id = id,
    characterName = characterName,
    description = description
)