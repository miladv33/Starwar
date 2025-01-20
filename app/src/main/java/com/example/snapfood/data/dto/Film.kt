package com.example.snapfood.data.dto

import com.google.gson.annotations.SerializedName

data class Film (
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("opening_crawl")
    val openingCrawl : String = "",
    val director: String = "",
    val producer: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    val characters: List<String> = emptyList(),
    val planets: List<String> = emptyList(),
    val starships: List<String> = emptyList(),
    val vehicles: List<String> = emptyList(),
    val species: List<String> = emptyList(),
    val created: String = "",
    val edited: String = "",
    val url: String = ""




)