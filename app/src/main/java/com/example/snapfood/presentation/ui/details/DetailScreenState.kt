package com.example.snapfood.presentation.ui.details


data class DetailScreenState(
    val characterId: String = "",
    val character: Detail = Detail(),
    val isLoading: Boolean = false,
)

data class Detail (
    val characterName: String = "",
    val language: String ="",
    val homeWorld: String ="",
    val population: String ="",
    val description: String ="",
    val basicInfo: List<InfoItem> = emptyList(),
    val speciesInfo: List<InfoItem> = emptyList(),
    val films: List<Film> = emptyList(),
)


data class InfoItem(
    val label: String,
    val value: String
)

data class Film(
    val title: String,
    val crawl: String
)

