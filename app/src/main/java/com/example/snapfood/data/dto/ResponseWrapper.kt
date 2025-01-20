package com.example.snapfood.data.dto

data class ResponseWrapper<T>(
    val count: Int=0,
    val next: String?=null,
    val previous:String?=null,
    val results: List<T> = listOf(),
)
