package com.example.snapfood.presentation.ui.search

sealed class SearchScreenNavigation {
    data class NavigateToDetails(val characterId: String) : SearchScreenNavigation()
}