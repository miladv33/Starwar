package com.example.snapfood.domain.model

data class StarWarsCharacter(
    val id: String,
    val name: String,
    val birthYear: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val gender: String,
    val homeWorld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>
) {
    fun getDescription(): String = buildString {
        append("Height: $height cm, ")
        append("Mass: $mass kg, ")
        append("Hair: $hairColor, ")
        append("Eyes: $eyeColor")
    }

    fun getBasicInfo(): List<Pair<String, String>> = listOf(
        "Birth Year" to birthYear,
        "Height" to "${height}cm",
        "Mass" to "${mass}kg",
        "Gender" to gender
    )

    fun getSpeciesInfo(): List<Pair<String, String>> = listOf(
        "Hair Color" to hairColor,
        "Eye Color" to eyeColor,
        "Skin Color" to skinColor
    )
}
