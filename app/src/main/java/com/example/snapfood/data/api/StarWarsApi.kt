package com.example.snapfood.data.api

import com.example.snapfood.data.dto.Character
import com.example.snapfood.data.dto.ResponseWrapper
import retrofit2.http.GET

interface StarWarsApi {

    @GET("people")
    suspend fun getPeople(): ResponseWrapper<Character>
}
