package com.example.snapfood.presentation.ui.details

sealed class DetailScreenEvent {
    data class OnGetDetailResult(val characterId: String): DetailScreenEvent()
}