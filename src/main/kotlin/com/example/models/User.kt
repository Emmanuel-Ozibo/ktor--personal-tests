package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    var firstName: String,
    var lastName: String,
    var email: String
)
