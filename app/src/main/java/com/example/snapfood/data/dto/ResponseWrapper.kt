package com.example.snapfood.data.dto

data class ResponseWrapper<T>(
    val result: List<T> = listOf(),
    val count: Int=0,
    val nest: String?=null,
    val previous:String?=null,
)
