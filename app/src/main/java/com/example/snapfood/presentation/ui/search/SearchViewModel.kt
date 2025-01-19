package com.example.snapfood.presentation.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapfood.domain.model.CharacterUiModel
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.usecase.SearchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    // Add a SharedFlow for navigation events
    private val _navigation = MutableSharedFlow<SearchScreenNavigation>()
    val navigation = _navigation.asSharedFlow()

    fun onEvent(event: SearchScreenEvent) = when (event) {
        is SearchScreenEvent.OnSearchQueryChange -> searchCharacters(event.query)
        is SearchScreenEvent.OnCharacterClick -> onCharacterSelected(event.characterId)
    }

    private fun searchCharacters(query: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    searchQuery = query,
                    isLoading = true
                )
            }
            searchCharactersUseCase(query).collect { result ->
                when (result) {
                    is Resources.Loading -> {
                        _state.update {
                            it.copy(
                                characters = emptyList(),
                                isLoading = true
                            )
                        }
                    }

                    is Resources.Error -> {
                        _state.update {
                            it.copy(
                                characters = emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is Resources.Success -> {
                        println("Data in View model  ${result.data}")
                        _state.update {
                            it.copy(
                                characters = result.data?.map { char ->
                                    CharacterUiModel(
                                        id = char.id,
                                        name = char.name,
                                        description = char.description
                                    )
                                } ?: emptyList(),
                                isLoading = false
                            )
                        }
                        Log.d("TAG", "searchCharacters: ${state.value}")
                    }
                }
            }
        }
    }

    private fun onCharacterSelected(characterId: String) {
        viewModelScope.launch {
            _navigation.emit(SearchScreenNavigation.NavigateToDetails(characterId))
        }
    }

}


