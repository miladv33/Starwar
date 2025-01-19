package com.example.snapfood.util

import javax.inject.Inject

class SecretFields @Inject constructor() {
    private val debugBaseUrl = "https://swapi.dev/api/"
    private val releaseBaseUrl = "https://swapi.dev/api/"


    //    todo remember to use BuildConfig.DEBUG
    private val isDebug: Boolean = true
    fun getBaseUrl(): String {
        return if (isDebug) {
            debugBaseUrl
        } else {
            releaseBaseUrl
        }
    }
}