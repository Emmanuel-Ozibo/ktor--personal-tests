package com.example.models.response

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

sealed class Response<out T> {

    @Serializable
    data class Success<out T>(
        val code: Int = HttpStatusCode.OK.value,
        val message: String = HttpStatusCode.OK.description,
        val data: T?
    ) : Response<T>()

    @Serializable
    data class Failure(val code: Int, val message: String) : Response<Nothing>()
}
