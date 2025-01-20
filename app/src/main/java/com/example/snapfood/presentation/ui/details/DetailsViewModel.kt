package com.example.snapfood.presentation.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapfood.domain.model.FilmInfo
import com.example.snapfood.domain.model.StarWarsCharacter
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.usecase.GetCharacterDetailsUseCase
import com.example.snapfood.domain.usecase.GetFilmInfoUseCase
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
class DetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getFilmInfoUseCase: GetFilmInfoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(DetailScreenState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("characterId")?.let { characterId ->
            onEvent(DetailScreenEvent.OnGetDetailResult(characterId))
        }
    }

    fun onEvent(event: DetailScreenEvent) {
        when (event) {
            is DetailScreenEvent.OnGetDetailResult -> {
                getCharacterDetails(event.characterId)
            }
        }
    }

    private fun getCharacterDetails(characterId: String) {
        viewModelScope.launch {
            getCharacterDetailsUseCase(characterId)
                .onStart { setLoading(true) }
                .collect { result ->
                    when (result) {
                        is Resources.Success -> {
                            updateUI(result.data)
                            result.data?.films?.let { loadFilmDetails(it) }
                        }
                        is Resources.Loading -> setLoading(result.isLoading)
                        is Resources.Error -> handleError()
                    }
                }
        }
    }

    private fun loadFilmDetails(filmIds: List<String>) {
        // Add films to loading state
        _state.update { it.copy(loadingFilms = it.loadingFilms + filmIds) }

        // Load each film's details
        filmIds.forEach { filmId ->
            viewModelScope.launch {
                getFilmInfoUseCase(filmId)
                    .collect { result ->
                        when (result) {
                            is Resources.Success -> {
                                result.data?.let { updateFilmInfo(it) }
                                removeFilmFromLoading(filmId)
                            }
                            is Resources.Error -> {
                                updateFilmError(filmId, result.message ?: "Unknown error")
                                removeFilmFromLoading(filmId)
                            }
                            is Resources.Loading -> {} // Already handled
                        }
                    }
            }
        }
    }

    private fun updateFilmInfo(filmInfo: FilmInfo) {
        _state.update { currentState ->
            currentState.copy(
                films = currentState.films + (filmInfo.id to filmInfo),
                errorFilms = currentState.errorFilms - filmInfo.id
            )
        }
    }

    private fun updateFilmError(filmId: String, error: String) {
        _state.update { currentState ->
            currentState.copy(
                errorFilms = currentState.errorFilms + (filmId to error)
            )
        }
    }

    private fun removeFilmFromLoading(filmId: String) {
        _state.update { it.copy(loadingFilms = it.loadingFilms - filmId) }
    }

    private fun setLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }

    private fun handleError() {
        _state.update { it.copy(isLoading = false, character = null) }
    }

    private fun updateUI(character: StarWarsCharacter?) {
        _state.update { it.copy(character = character, isLoading = false) }
    }
}