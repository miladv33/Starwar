package com.example.snapfood.data.api

import com.example.snapfood.data.dto.Character
import com.example.snapfood.data.dto.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun getPeople(
        @Query("search") keyword: String = ""
    ): ResponseWrapper<Character>

    @GET("people/{characterId}")
    suspend fun  getCharacterDetail(
        @Path("characterId") characterId: String
    ) : Character
}
