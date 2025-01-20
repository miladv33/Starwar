package com.example.snapfood.data.api

import com.example.snapfood.data.dto.StarWarsCharacterDto
import com.example.snapfood.data.dto.Film
import com.example.snapfood.data.dto.Planet
import com.example.snapfood.data.dto.ResponseWrapper
import com.example.snapfood.data.dto.Species
import com.example.snapfood.data.dto.Starship
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {
    @GET("people/")
    suspend fun getPeople(
        @Query("search") keyword: String = ""
    ): ResponseWrapper<StarWarsCharacterDto>

    @GET("people/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: String
    ): StarWarsCharacterDto

    @GET("films/{filmId}")
    suspend fun getFilm(
        @Path("filmId") filmId: String
    ): Film

    @GET("planets/{planetId}")
    suspend fun getPlanet(
        @Path("planetId") planetId: String
    ): Planet

    @GET("starships/{starshipId}")
    suspend fun getStarship(
        @Path("starshipId") starshipId: String
    ): Starship

    @GET("species/{specieId}")
    suspend fun getSpecies(
        @Path("specieId") specieId: String
    ): Species
}