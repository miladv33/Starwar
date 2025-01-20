package com.example.snapfood.presentation.ui.details

import com.example.snapfood.domain.model.FilmInfo

data class FilmUiState(
    val id: String,
    val filmInfo: FilmInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)