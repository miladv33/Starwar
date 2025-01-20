package com.example.snapfood.data.dto

import com.google.gson.annotations.SerializedName

data class Species(
    @SerializedName("name")
    val name: String,

    @SerializedName("classification")
    val classification: String,

    @SerializedName("designation")
    val designation: String,

    @SerializedName("average_height")
    val averageHeight: String,

    @SerializedName("skin_colors")
    val skinColors: String,

    @SerializedName("hair_colors")
    val hairColors: String,

    @SerializedName("eye_colors")
    val eyeColors: String,

    @SerializedName("average_lifespan")
    val averageLifespan: String,

    @SerializedName("homeworld")
    val homeworld: String,

    @SerializedName("language")
    val language: String,

    @SerializedName("people")
    val people: List<String>,

    @SerializedName("films")
    val films: List<String>,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String,

    @SerializedName("url")
    val url: String
)
