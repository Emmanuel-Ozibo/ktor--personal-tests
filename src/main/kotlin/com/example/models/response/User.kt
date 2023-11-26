package com.example.models.response

import io.ktor.server.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    val token: String
) : Principal
