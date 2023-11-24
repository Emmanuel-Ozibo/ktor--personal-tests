package com.example.models.response

import io.ktor.http.HttpStatusCode

sealed class Response<T>(
    val code: Int,
    val message: String,
    val data: T? = null
) {
    class Success<T>(data: T?): Response<T>(code = HttpStatusCode.OK.value, data = data,
        message = HttpStatusCode.OK.description)

    class Failure(code: Int, message: String): Response<Nothing>(
        code, message
    )
}
