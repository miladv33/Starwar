package com.example.snapfood.presentation.ui.details

import androidx.lifecycle.ViewModel
import com.example.snapfood.domain.usecase.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(  // Add @Inject here
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

}
