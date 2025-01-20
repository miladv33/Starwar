package com.example.snapfood.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.usecase.GetAllCharactersUseCase
import com.example.snapfood.domain.usecase.SearchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val searchDebouncer: SearchDebouncer
) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    init {
        onEvent(SearchScreenEvent.LoadInitialCharacters)
        setupSearchDebouncing()
    }

    private fun setupSearchDebouncing() {
        viewModelScope.launch {
            searchDebouncer.getQueryFlow()
                .collect { query ->
                    if (query.isNotBlank()) {
                        performSearch(query)
                    } else {
                        clearSearchResults()
                    }
                }
        }
    }

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchQueryChange -> updateSearchQuery(event.query)
            SearchScreenEvent.LoadInitialCharacters -> loadInitialCharacters()
        }
    }

    private fun loadInitialCharacters() {
        viewModelScope.launch {
            getAllCharactersUseCase()
                .onStart { setLoading(true) }
                .catch { error ->
                    handleError(error)
                }
                .collect { result ->
                    handleSearchResult(result)
                }
        }
    }

    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
        searchDebouncer.setQuery(query)
    }

    private suspend fun performSearch(query: String) {
        searchCharactersUseCase(query)
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

    private fun handleSearchResult(result: Resources<List<StarWarsCharacter>>) {
        when (result) {
            is Resources.Success -> updateCharactersList(result.data)
            is Resources.Error -> handleSearchError()
            is Resources.Loading -> setLoading(result.isLoading)
        }
    }

    private fun updateCharactersList(characters: List<StarWarsCharacter>?) {
        _state.update { currentState ->
            currentState.copy(
                characters = characters?.map { it } ?: emptyList(),
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

