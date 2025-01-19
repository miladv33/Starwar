package com.example.snapfood.presentation.ui.details

import android.icu.text.IDNA.Info
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snapfood.domain.model.CharacterUiModel
import com.example.snapfood.domain.model.Resources
import com.example.snapfood.domain.usecase.GetCharacterDetailsUseCase
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
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailScreenState())
    val state = _state.asStateFlow()
    private var job: Job? = null

    fun onEvent(event: DetailScreenEvent) {
        when (event) {
            is DetailScreenEvent.OnGetDetailResult -> {
                updateGetDetailQuery(event.characterId)
            }
        }
    }
    init {
        updateGetDetailQuery("13")
    }
    private fun updateGetDetailQuery(characterId: String) {
        _state.update { it.copy(characterId = characterId) }
        job?.cancel()
        job = viewModelScope.launch { getCharacterDetail(characterId) }
    }

    private fun setLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
    private fun handleError() {
        _state.update {
            it.copy(
                isLoading = false,
                character = Detail(),
            )
        }
    }

    private fun handleResult(result: Resources<CharacterUiModel>) {
        when(result){
            is Resources.Success -> {updateUI(result.data)}
            is Resources.Loading -> {setLoading(result.isLoading)}
            is Resources.Error ->{handleError()}
        }
    }

    private fun updateUI(character: CharacterUiModel?) {
        _state.update { currentState ->
            currentState.copy(
                character = character?.toDetail() ?: Detail(),
                isLoading = false
            )
        }
    }


    private suspend fun getCharacterDetail(characterId: String) {
        getCharacterDetailsUseCase(characterId)
            .onStart { setLoading(true) }
            .catch { error ->
                handleError()
            }
            .collect{ result ->
                handleResult(result)
            }
    }
    private fun CharacterUiModel.toDetail() = Detail(
        basicInfo = listOf(InfoItem("Birth Year", birthYear), InfoItem("Hieght", height)),
        speciesInfo = listOf(InfoItem("Species", homeWorld), InfoItem("Population", population)),
        characterName = characterName,
        description = description
    )
}