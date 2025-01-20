package com.example.snapfood.data.api

import com.example.snapfood.data.dto.StarWarsCharacterDto
import com.example.snapfood.data.dto.Film
import com.example.snapfood.data.dto.ResponseWrapper
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
    suspend fun  getCharacterDetail(
        @Path("characterId") characterId: String
    ) : StarWarsCharacterDto

    @GET("film/filmId")
    suspend fun getFilm(
        @Path("filmId") filmId: String
    ) : Film

    @GET("planets/planetId")
    suspend fun getPlanet(
        @Path("planetId") filmId: String
    ) : Film

    @GET("starships/starshipId")
    suspend fun getStarship(
        @Path("starshipId") filmId: String
    ) : Starship

    @GET("species/specieId")
    suspend fun getSpecies(
        @Path("starshipId") filmId: String
    ) : Starship
}
