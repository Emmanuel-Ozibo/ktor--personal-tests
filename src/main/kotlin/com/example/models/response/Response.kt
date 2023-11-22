package com.example.models.response

data class Response<T>(
    val code: Int,
    val message: String,
    val data: T?
)
