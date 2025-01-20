package com.example.snapfood.data.dto

import com.google.gson.annotations.SerializedName

data class Planet(
    val name: String = "",
    @SerializedName("rotation_period")
    val rotationPeriod : String = "",
    @SerializedName("orbital_period")
    val orbitalPeriod : String = "",
    val diameter : String = "",
    val climate : String = "",
    val gravity : String = "",
    val terrain : String = "",
    val surface_water : String = "",
    val population : String = "",
    val residents : List<String> = emptyList(),
    val films : List<String> = emptyList(),
    val created: String = "",
    val edited: String = "",
    val url: String = ""
)
