package com.example.snapfood.presentation.ui.search

sealed class SearchScreenEvent {
    data class OnSearchQueryChange(val query: String) : SearchScreenEvent()
}